package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.FlightDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Flight;
import com.codecool.fas.model.FlightQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightJson implements FlightDao {

    Database database;

    public FlightJson(Database database) {
        this.database = database;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person) {
        List<Flight> flights = database.getFlights();
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
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person, List<String> airlineCode, Integer priceFrom, Integer priceTo) {
        return null;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person) {
        return null;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person, List<String> airlineCode, Integer priceFrom, Integer priceTo) {
        return null;
    }
}
