package com.codecool.fas.dao.implementations.jpa;

import com.codecool.fas.dao.interfaces.AirportDao;
import com.codecool.fas.entity.Airport;
import com.codecool.fas.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("AirportJpa")
public class AirportJpa implements AirportDao {

    private final AirportRepository airportRepository;

    public AirportJpa(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAirportsBySubstring(String subString) {
        return airportRepository.findByLabelIsContainingIgnoreCase(subString);
    }
}
