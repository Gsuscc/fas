package com.codecool.fas;

import com.codecool.fas.memory.JsonToPSQL;
import com.codecool.fas.repository.AirlineRepository;
import com.codecool.fas.repository.AirportRepository;
import com.codecool.fas.repository.CityRepository;
import com.codecool.fas.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FakeAirlineServicesApplication {
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(FakeAirlineServicesApplication.class, args);
    }

}
