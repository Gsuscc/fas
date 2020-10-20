package com.codecool.fas.dao.implementations.jpa;

import com.codecool.fas.dao.interfaces.TripAdviseDao;
import com.codecool.fas.entity.Airport;
import com.codecool.fas.entity.City;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.model.TripAdvise;
import com.codecool.fas.repository.AirportRepository;
import com.codecool.fas.repository.CityRepository;
import com.codecool.fas.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Qualifier("tripAdviseJpa")
public class TripAdviseJpa implements TripAdviseDao {

    private static final int DEFAULT_AIRPORT = 6;
    private static final int AFTER_DAYS = 1;
    private static final int BEFORE_DAYS = 5;
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;


    public TripAdviseJpa(CityRepository cityRepository, AirportRepository airportRepository, FlightRepository flightRepository) {
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<TripAdvise> getAdvices(String country) {
        List<TripAdvise> tripAdvises = new ArrayList<>();
        List<City> cities = cityRepository.findByCityImageNotNull();
        Airport fromAirport = cityRepository.
                findFirstByCountryNameEqualsIgnoreCase(country).
                getAirports().
                stream().
                findFirst().
                get();

        Collections.shuffle(cities);

        cities.forEach(city -> {
            if (!city.getCityName().equals(fromAirport.getCity().getCityName())) {
                Airport toAirport = city.
                        getAirports().
                        stream().
                        findFirst().
                        get();

                Flight flight = flightRepository.findCheapestFlight(fromAirport, toAirport, LocalDate.now().plusDays(BEFORE_DAYS), LocalDate.now().plusDays(AFTER_DAYS)).
                        stream().
                        findFirst().
                        orElse(null);
                if (flight != null) {
                    addAdvise(tripAdvises, city, flight, fromAirport, toAirport);
                }
            }
        });
        return tripAdvises;
    }

    private void addAdvise(List<TripAdvise> tripAdvises, City city, Flight flight, Airport fromAirport, Airport toAirport) {
        tripAdvises.add(
                new TripAdvise(
                        city.getCityImage().toString(),
                        fromAirport,
                        toAirport,
                        flight.getDeparture().getYear(),
                        flight.getDeparture().getMonthValue(),
                        flight.getDeparture().getDayOfMonth(),
                        String.valueOf(flight.getTouristPrice().intValue()),
                        city.getCityName(),
                        city.getCountryName()
                )
        );
    }
}
