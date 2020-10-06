package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.TripAdviseDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.City;
import com.codecool.fas.model.Flight;
import com.codecool.fas.model.TripAdvise;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TripAdviseJson implements TripAdviseDao {

    private static final int DEFAULT_QUANTITY = 5;
    private static final int AFTER_DAYS = 1;
    private static final int BEFORE_DAYS = 5;
    private Database database;


    public TripAdviseJson(Database database) {
        this.database = database;
    }

    @Override
    public List<TripAdvise> getAdvices(String fromCountry) {
        List<TripAdvise> tripAdvises = new ArrayList<>();
        List<Flight> flights = database.getFlights();
        List<City> cities = database.getCities();
        List<Airport> airports = database.getAirports();
        Airport fromAirport = getFromAirport(fromCountry, airports);

        Collections.shuffle(cities);

        cities.forEach(city -> {
                    Airport toAirport = getToAirport(airports, city);
                    Flight flight = getCheapestFlight(flights, fromAirport, toAirport);
                    addAdvise(tripAdvises, city, flight);
                }
        );
        return tripAdvises;
    }

    private void addAdvise(List<TripAdvise> tripAdvises, City city, Flight flight) {
        tripAdvises.add(new TripAdvise(
                        city.getCityImage().toString(),
                        String.format("/airport/query?fromCode=%s&toCode=%s&tripDate=%s&person=1",
                                flight.getFromCode(),
                                flight.getToCode(),
                                flight.getDeparture().toLocalDate().toString()),
                        flight.getTouristPrice(),
                        city.getCityName(),
                        city.getCountryName()
                )
        );
    }

    private Flight getCheapestFlight(List<Flight> flights, Airport fromAirport, Airport toAirport) {
        return flights.stream()
                .filter(advisorFlights -> advisorFlights.getFromCode().equals(fromAirport.getCode())
                        && advisorFlights.getToCode().equals(toAirport.getCode())
                        && advisorFlights.getDeparture().isAfter(LocalDateTime.now().plusDays(AFTER_DAYS))
                        && advisorFlights.getDeparture().isBefore(LocalDateTime.now().plusDays(BEFORE_DAYS)))
                .min((o1, o2) -> (int) (o1.getBusinessPrice() - o2.getBusinessPrice()))
                .orElseThrow();
    }

    private Airport getFromAirport(String fromCountry, List<Airport> airports) {
        return airports.stream()
                .filter(airport -> airport.getCountryName().equals(fromCountry))
                .findFirst()
                .orElse(airports.get(DEFAULT_QUANTITY));
    }

    private Airport getToAirport(List<Airport> airports, City city) {
        return airports.stream()
                .filter(airport -> airport.getCityName().equals(city.getCityName()))
                .findFirst()
                .orElseThrow();
    }
}
