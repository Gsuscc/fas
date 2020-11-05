package com.codecool.fas.controller;

import com.codecool.fas.entity.*;
import com.codecool.fas.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private CityRepository cityRepository;
    private UserCityRepository userCityRepository;

    public FavouriteController(FlightRepository flightRepository, UserRepository userRepository, BookedFlightRepository bookedFlightRepository, CityRepository cityRepository, UserCityRepository userCityRepository) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.bookedFlightRepository = bookedFlightRepository;
        this.cityRepository = cityRepository;
        this.userCityRepository = userCityRepository;
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
        UserInfo user = getUserInfo();
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
        List<BookedTicket> returnTickets = returnFlight == null
                ? new ArrayList<>()
                : generateTickets(returnFlight, bookedFlight, person);

        bookedFlight.setTickets(getCombinedBookedTickets(toTickets, returnTickets));

        bookedFlightRepository.save(bookedFlight);

        return ResponseEntity.ok("Success");
    }


    @GetMapping("/flight")
    private ResponseEntity getBookedFlights(){
        try {
            UserInfo user = getUserInfo();
            List<BookedFlight> bookedFlights = bookedFlightRepository.findAllByUserIs(user);
            return ResponseEntity.ok(bookedFlights);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while getting tickets!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addCity")
    private ResponseEntity addCityToFavourites (@RequestParam Long id){
        UserInfo user = getUserInfo();
        City city = cityRepository.findById(id).get();
        UserCity userCity = UserCity.builder().city(city).userInfo(user).build();

        if (userCityRepository.existsByCityAndUserInfo(city, user)){
            return new ResponseEntity<>("Already liked", HttpStatus.BAD_REQUEST);
        }
            userCityRepository.save(userCity);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/removeCity")
    private ResponseEntity removeCityFromFavourites (@RequestParam Long id){
        UserInfo user = getUserInfo();
        City city = cityRepository.findById(id).get();
        userCityRepository.deleteUserCityByCityIsAndUserInfoIs(city, user);
        return ResponseEntity.ok("Success");
    }

    private UserInfo getUserInfo() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(userName).get();
    }


    @GetMapping("/getCities")
    private ResponseEntity getAllUserCities () {
        UserInfo user = getUserInfo();
        List<UserCity> cities = userCityRepository.findAllByUserInfoIs(user);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/notify")
    private ResponseEntity setNotificationForACity(@RequestParam Long id, @RequestParam Boolean isRequested){

        userCityRepository.updateNotification(isRequested,getUserInfo(),cityRepository.findById(id).get());

        return ResponseEntity.ok("Success");
    }


    private List<BookedTicket> getCombinedBookedTickets(List<BookedTicket> toTickets, List<BookedTicket> returnTickets) {
        return IntStream.range(0, Math.max(toTickets.size(), returnTickets.size()))
                    .boxed()
                    .flatMap(i -> {
                        if (i < Math.min(toTickets.size(), returnTickets.size()))
                            return Stream.of(toTickets.get(i), returnTickets.get(i));
                        else if (i < toTickets.size())
                            return Stream.of(toTickets.get(i));
                        return Stream.of(returnTickets.get(i));
                    })
                    .collect(Collectors.toList());
    }
}
