package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.Airport;

import java.util.List;

public interface AirportDao {
    List<Airport> getAirportsBySearch(String subString);
}
