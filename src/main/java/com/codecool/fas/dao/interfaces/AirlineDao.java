package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.Airline;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AirlineDao {

    Airline getAirlineByCode(String airlineCode);
    List<Airline> getAirlines();

}
