package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirportDao {
    List<Airport> getAirportsBySubstring(String subString);
}
