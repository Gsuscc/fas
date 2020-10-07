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

    private static final int DEFAULT_AIRPORT = 2;
    private static final int AFTER_DAYS = 1;
    private static final int BEFORE_DAYS = 5;
    private final Database database;


    public TripAdviseJson(Database database) {
        this.database = database;
    }

    @Override
    public List<TripAdvise> getAdvices() {
        List<TripAdvise> tripAdvises = new ArrayList<>();
        List<Flight> flights = getTrimmedList();
        List<City> cities = database.getCities();
        List<Airport> airports = database.getAirports();
        Airport fromAirport = getFromAirport(airports);

        Collections.shuffle(cities);

        cities.forEach(city -> {
            if(!city.getCityName().equals(fromAirport.getCityName())) {
                Airport toAirport = getToAirport(airports, city);
                Flight flight = getCheapestFlight(flights, fromAirport, toAirport);
                addAdvise(tripAdvises, city, flight);
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

    private void addAdvise(List<TripAdvise> tripAdvises, City city, Flight flight) {
        tripAdvises.add(
            new TripAdvise(
                city.getCityImage().toString(),
                String.format("/airport/query?fromCode=%s&toCode=%s&tripDate=%s&person=1",
                        flight.getFromCode(),
                        flight.getToCode(),
                        flight.getDeparture().toLocalDate().toString()
                ),
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

    private Airport getFromAirport(List<Airport> airports) {
//        return airports.stream()
//                .filter(airport -> airport.getCountryName().equals(fromcountry...))
//                .findFirst()
//                .orElse(airports.get(DEFAULT_QUANTITY));
        return airports.get(DEFAULT_AIRPORT);
    }

    private Airport getToAirport(List<Airport> airports, City city) {
        return airports.stream()
                .filter(airport -> airport.getCityName().equals(city.getCityName()))
                .findFirst()
                .orElseThrow();
    }
}
