package com.codecool.fas.dao.implementations.jpa;

import com.codecool.fas.dao.interfaces.FlightDao;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.model.FlightQuery;
import com.codecool.fas.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("FlightJpa")
public class FlightJpa implements FlightDao {

    FlightRepository flightRepository;

    public FlightJpa(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person) {
        List<FlightQuery> result = new ArrayList<>();
        List<Flight> flightList = flightRepository.findFlightsOneWay(departureCode, arriveCode, tripDate);
        flightList.forEach(flight -> result.add(new FlightQuery(flight, null, person)));
        return result;

    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
        return null;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person) {
        return null;
    }

    @Override
    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
        return null;
    }
}
