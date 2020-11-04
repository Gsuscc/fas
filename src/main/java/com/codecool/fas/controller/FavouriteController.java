package com.codecool.fas.controller;

import com.codecool.fas.entity.BookedFlight;
import com.codecool.fas.entity.BookedTicket;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.entity.UserInfo;
import com.codecool.fas.repository.BookedFlightRepository;
import com.codecool.fas.repository.FlightRepository;
import com.codecool.fas.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    private List<BookedTicket> generateTickets(Flight flight, BookedFlight bookedFlight, int amount) {
        return IntStream.range(0, amount).boxed().map(x ->
                BookedTicket.builder()
                        .flight(flight)
                        .bookedFlight(bookedFlight)
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("/book")
    private ResponseEntity bookFlight(@RequestParam Long id ,@RequestParam(required = false) Long returnId, @RequestParam Integer person){
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();


        UserInfo user = userRepository.findByUsername(userName).get();
        Flight toFlight = flightRepository.getOne(id);
        Flight returnFlight = returnId != null ? flightRepository.getOne(returnId) : null;

        BookedFlight bookedFlight = BookedFlight.builder()
                .bookedAt(LocalDateTime.now())
                .user(user)
                .passengers(person)
                .fromAirport(toFlight.getFromAirport().getLabel())
                .toAirport(toFlight.getToAirport().getLabel())
                .build();

        List<BookedTicket> toTickets = generateTickets(toFlight, bookedFlight, person);
        List<BookedTicket> returnTickets = generateTickets(returnFlight, bookedFlight, person);

        bookedFlight.setTickets(Stream.concat(toTickets.stream(), returnTickets.stream())
                .collect(Collectors.toList()));

        bookedFlightRepository.save(bookedFlight);

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
