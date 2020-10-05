package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightDao {

    List<Flight> getFlights(String departureCode, String arriveCode, LocalDateTime departure, LocalDateTime arrival);

}
