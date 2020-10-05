package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.Airline;

import java.util.List;

public interface AirlineDao {

    Airline getAirlineByCode(String airlineCode);
    List<Airline> getAirlines();

}
