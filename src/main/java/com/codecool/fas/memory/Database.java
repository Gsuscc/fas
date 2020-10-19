package com.codecool.fas.memory;

import com.codecool.fas.model.Airline;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.City;
import com.codecool.fas.model.Flight;
import com.codecool.fas.util.FileHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Scope("singleton")
public class Database {

    private final List<Airline> airlines = new ArrayList<>();
    private final List<Airport> airports = new ArrayList<>();
    private final List<Flight> flights = new ArrayList<>();
    private final List<City> cities = new ArrayList<>();

    public Database() {

        Gson gson = new Gson();

//        String jsonAirlines = FileHandler.read("src/main/resources/data/airlines.json");
//        String jsonAirports = FileHandler.read("src/main/resources/data/airports.json");
//        String jsonFlights = FileHandler.read("src/main/resources/data/flights.json");
//        String jsonCities = FileHandler.read("src/main/resources/data/cities.json");

        Type airlinesListType = new TypeToken<Collection<Airline>>() {
        }.getType();
        Type airportsListType = new TypeToken<Collection<Airport>>() {
        }.getType();
        Type flightListType = new TypeToken<Collection<Flight>>() {
        }.getType();
        Type citiesListType = new TypeToken<Collection<City>>() {
        }.getType();

//        this.airlines = gson.fromJson(jsonAirlines, airlinesListType);
//        this.airports = gson.fromJson(jsonAirports, airportsListType);
//        this.flights = gson.fromJson(jsonFlights, flightListType);
//        this.cities = gson.fromJson(jsonCities, citiesListType);

        for (Flight flight: this.flights) {
            flight.setTravelTime(
                    flight.getArrival().toLocalTime()
                            .minusHours(flight.getDeparture().getHour())
                            .minusMinutes(flight.getDeparture().getMinute())
                            .minusSeconds(flight.getDeparture().getSecond())
            );
        }
    }

    public List<Airline> getAirlines() {
        return airlines;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<City> getCities() {
        return cities;
    }
}
