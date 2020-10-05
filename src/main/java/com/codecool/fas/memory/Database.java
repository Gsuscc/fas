package com.codecool.fas.memory;

import com.codecool.fas.model.Airline;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.Flight;
import com.codecool.fas.util.FileHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

@Service
@Scope("singleton")
public class Database {

    private final List<Airline> airlines;
    private final List<Airport> airports;
    private final List<Flight> flights;

    public Database() {

        Gson gson = new Gson();

        String jsonAirlines = FileHandler.read("src/main/resources/data/airlines.json");
        String jsonAirports = FileHandler.read("src/main/resources/data/airports.json");
        String jsonFlights = FileHandler.read("src/main/resources/data/flights_dummy.json");

        Type airlinesListType = new TypeToken<Collection<Airline>>() {}.getType();
        Type airportsListType = new TypeToken<Collection<Airport>>() {}.getType();
        Type flightListType = new TypeToken<Collection<Flight>>() {}.getType();

        this.airlines = gson.fromJson(jsonAirlines, airlinesListType);
        this.airports = gson.fromJson(jsonAirports, airportsListType);
        this.flights = gson.fromJson(jsonFlights, flightListType);

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
}
