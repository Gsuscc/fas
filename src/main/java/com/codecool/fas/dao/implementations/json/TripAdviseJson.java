package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.TripAdviseDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.City;
import com.codecool.fas.model.Flight;
import com.codecool.fas.model.TripAdvise;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TripAdviseJson implements TripAdviseDao {

    private static final int DEFAULT_AIRPORT = 6;
    private static final int AFTER_DAYS = 1;
    private static final int BEFORE_DAYS = 5;
    private final Database database;
    private final List<Airport> airports;

    public TripAdviseJson(Database database) {
        this.database = database;
        this.airports = database.getAirports();
    }

    @Override
    public List<TripAdvise> getAdvices(String country) {
        List<TripAdvise> tripAdvises = new ArrayList<>();
        List<Flight> flights = getTrimmedList();
        List<City> cities = database.getCities();
        Airport fromAirport = getFromAirport(country);

        Collections.shuffle(cities);

        cities.forEach(city -> {
            if(!city.getCityName().equals(fromAirport.getCityName())) {
                Airport toAirport = getToAirport(city);
                Flight flight = getCheapestFlight(flights, fromAirport, toAirport);
                addAdvise(tripAdvises, city, flight, fromAirport, toAirport);
            }
        });
        return tripAdvises;
    }

    private List<Flight> getTrimmedList() {
        return database.getFlights().stream().filter(advisorFlights ->
                advisorFlights.getDeparture().isAfter(LocalDateTime.now().plusDays(AFTER_DAYS)) &&
                        advisorFlights.getDeparture().isBefore(LocalDateTime.now().plusDays(BEFORE_DAYS))
        ).collect(Collectors.toList());
    }

    private void addAdvise(List<TripAdvise> tripAdvises, City city, Flight flight, Airport fromAirport, Airport toAirport) {
        tripAdvises.add(
                new TripAdvise(
                        city.getCityImage().toString(),
                        fromAirport,
                        toAirport,
                        flight.getDeparture().getYear(),
                        flight.getDeparture().getMonthValue(),
                        flight.getDeparture().getDayOfMonth(),
                        String.valueOf(flight.getTouristPrice().intValue()),
                        city.getCityName(),
                        city.getCountryName()
                )
        );
    }

    private Flight getCheapestFlight(List<Flight> flights, Airport fromAirport, Airport toAirport) {
        return flights.stream()
                .filter(advisorFlights -> advisorFlights.getFromCode().equals(fromAirport.getCode())
                        && advisorFlights.getToCode().equals(toAirport.getCode()))
                .min(Comparator.comparingDouble(Flight::getTouristPrice))
                .orElseThrow();
    }

    private Airport getFromAirport(String country) {
        return airports.stream()
                .filter(airport -> airport.getCountryName().equals(country))
                .findFirst()
                .orElse(airports.get(DEFAULT_AIRPORT));
    }

    private Airport getToAirport(City city) {
        return airports.stream()
                .filter(airport -> airport.getCityName().equals(city.getCityName()))
                .findFirst()
                .orElseThrow();
    }
}
