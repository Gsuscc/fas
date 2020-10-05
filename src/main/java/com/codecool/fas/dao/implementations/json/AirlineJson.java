package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.AirlineDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Airline;

import java.util.List;

public class AirlineJson implements AirlineDao {

    Database database;

    @Override
    public Airline getAirlineByCode(String airlineCode) {
        return database.getAirlines().
                stream().
                filter(airline -> airlineCode.equals(airline.getCode())).
                findFirst().
                orElseThrow();
    }

    @Override
    public List<Airline> getAirlines() {
        return database.getAirlines();
    }
}
