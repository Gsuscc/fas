package com.codecool.fas.controller;

import com.codecool.fas.entity.BookedFlight;
import com.codecool.fas.repository.BookedFlightRepository;
import com.codecool.fas.repository.FlightRepository;
import com.codecool.fas.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity bookFlight(@RequestParam Long id ,@RequestParam Long returnId){
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        BookedFlight bookedFlight = BookedFlight.builder()
                .toFlight(flightRepository.getOne(id))
                .returnFlight(returnId != null ? flightRepository.getOne(returnId) : null)
                .user(userRepository.findByUsername(userName).get())
                .build();


        bookedFlightRepository.save(bookedFlight);

        return ResponseEntity.ok("Success");
    }
}
