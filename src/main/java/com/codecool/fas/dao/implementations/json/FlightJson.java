package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.FlightDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Flight;
import com.codecool.fas.model.FlightQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightJson implements FlightDao {

    Database database;
    private final List<Flight> flights;

    public FlightJson(Database database) {
        this.database = database;
        flights = database.getFlights();
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person) {
        List<FlightQuery> result = new ArrayList<>();
        flights.forEach(flight -> {
            if (flight.getFromCode().equals(departureCode)
                    && flight.getToCode().equals(arriveCode)
                    && flight.getDeparture().toLocalDate().equals(tripDate)) {
                result.add(new FlightQuery(flight, null, person));
            }
        });
        return result;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
        List<FlightQuery> result = new ArrayList<>();
        flights.forEach(flight -> {
            if (flight.getFromCode().equals(departureCode)
                    && flight.getToCode().equals(arriveCode)
                    && flight.getDeparture().toLocalDate().equals(tripDate)
                    && airlineCode.contains(flight.getAirline().getCode())
                    && flight.getTouristPrice() < priceTo
                    && flight.getTouristPrice() > priceFrom
                    && flight.getDeparture().toLocalTime().isAfter(timeFrom)
                    && flight.getDeparture().toLocalTime().isAfter(timeTo)
            ) {
                result.add(new FlightQuery(flight, null, person));
            }
        });
        return result;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person) {
        List<FlightQuery> result = new ArrayList<>();

        List<Flight> toFlights = flights.stream().filter(flight -> flight.getFromCode().equals(departureCode)
                && flight.getToCode().equals(arriveCode)
                && flight.getDeparture().toLocalDate().equals(tripDate)).collect(Collectors.toList());


        List<Flight> backFlights = flights.stream().filter(flight -> flight.getFromCode().equals(arriveCode)
                && flight.getToCode().equals(departureCode)
                && flight.getDeparture().toLocalDate().equals(tripBackDate)).collect(Collectors.toList());

        toFlights.forEach(flight -> {
            backFlights.forEach(backFlight -> {
                result.add(new FlightQuery(flight, backFlight, person));
            });
        });

        return result;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person, List<String> airlineCode, Integer priceFrom, Integer priceTo) {
        return null;
    }
}
