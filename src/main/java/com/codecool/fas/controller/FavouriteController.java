package com.codecool.fas.controller;

import com.codecool.fas.entity.BookedFlight;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.entity.UserInfo;
import com.codecool.fas.repository.BookedFlightRepository;
import com.codecool.fas.repository.FlightRepository;
import com.codecool.fas.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
@RequestMapping("/favourite")
public class FavouriteController {


    private FlightRepository flightRepository;
    private UserRepository userRepository;
    private BookedFlightRepository bookedFlightRepository;

    public FavouriteController(FlightRepository flightRepository, UserRepository userRepository, BookedFlightRepository bookedFlightRepository) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.bookedFlightRepository = bookedFlightRepository;
    }


    @GetMapping("/book")
    private ResponseEntity bookFlight(@RequestParam Long id ,@RequestParam(required = false) Long returnId, @RequestParam Integer person){
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();


        UserInfo user = userRepository.findByUsername(userName).get();
        Flight toFlight = flightRepository.getOne(id);
        Flight returnFlight = returnId != null ? flightRepository.getOne(returnId) : null;

        List<BookedFlight> bookedFlights = IntStream.range(0, person).boxed().map(x ->
                     BookedFlight.builder()
                            .toFlight(toFlight)
                            .returnFlight(returnFlight)
                            .user(user)
                            .build()
        ).collect(Collectors.toList());
        bookedFlightRepository.saveAll(bookedFlights);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/flight")
    private ResponseEntity getBookedFlights(){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            UserInfo user = userRepository.findByUsername(userName).get();
            List<BookedFlight> bookedFlights = bookedFlightRepository.findAllByUserIs(user);
            return ResponseEntity.ok(bookedFlights);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while getting tickets!", HttpStatus.BAD_REQUEST);
        }
    }

}
