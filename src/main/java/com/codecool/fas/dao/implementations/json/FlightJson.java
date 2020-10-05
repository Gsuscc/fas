package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.FlightDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.FlightQuery;

import java.time.LocalDate;
import java.util.List;

public class FlightJson implements FlightDao {

    Database database;

    public FlightJson(Database database) {
        this.database = database;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person) {
        return null;
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
