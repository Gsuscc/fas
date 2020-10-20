package com.codecool.fas.memory;

import com.codecool.fas.entity.Airline;
import com.codecool.fas.entity.Airport;
import com.codecool.fas.entity.City;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.repository.AirportRepository;
import com.codecool.fas.util.FileHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Scope("singleton")
public class JsonToPSQL {

    private final List<com.codecool.fas.model.Airline> airlines;
    private final List<com.codecool.fas.model.Airport> airports;
    private final List<com.codecool.fas.model.City> cities;
    private final List<Airline> airlineEntities = new ArrayList<>();
    private final List<Airport> airportEntities = new ArrayList<>();
    private final List<City> cityEntities = new ArrayList<>();
    private final List<Flight> flightEntities = new ArrayList<>();


    private final AirportRepository airportRepository;
    @Autowired
    public JsonToPSQL(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
        Gson gson = new Gson();

        String jsonAirlines = FileHandler.read("src/main/resources/data/airlines.json");
        String jsonAirports = FileHandler.read("src/main/resources/data/airports.json");
        String jsonCities = FileHandler.read("src/main/resources/data/cities.json");

        Type airlinesListType = new TypeToken<Collection<com.codecool.fas.model.Airline>>() {
        }.getType();
        Type airportsListType = new TypeToken<Collection<com.codecool.fas.model.Airport>>() {
        }.getType();
        Type citiesListType = new TypeToken<Collection<com.codecool.fas.model.City>>() {
        }.getType();

        this.airlines = gson.fromJson(jsonAirlines, airlinesListType);
        this.airports = gson.fromJson(jsonAirports, airportsListType);
        this.cities = gson.fromJson(jsonCities, citiesListType);

        for (com.codecool.fas.model.City city: this.cities) {
            this.cityEntities.add(City.builder()
                    .cityName(city.getCityName())
                    .countryName(city.getCountryName())
                    .cityImage(city.getCityImage())
                    .build()
            );
        }

        for (com.codecool.fas.model.Airline airline: this.airlines) {
            this.airlineEntities.add(Airline.builder()
                    .code(airline.getCode())
                    .name(airline.getName())
                    .logo(airline.getLogo())
                    .build()
            );
        }

        for (com.codecool.fas.model.Airport airport: this.airports) {
            City city = cityEntities.stream().filter(c -> c.getCityName().equals(airport.getCityName()) &&
                    c.getCountryName().equals(airport.getCountryName())).findFirst().get();

            Airport airportEntity = Airport.builder()
                    .label(airport.getLabel())
                    .airportName(airport.getAirportName())
                    .code(airport.getCode())
                    .countryCode(airport.getCountryCode())
                    .latitude(airport.getLatitude())
                    .longitude(airport.getLongitude())
                    .build();
            airportEntities.add(airportEntity);

            airportEntity.setCity(city);

        }
        airportRepository.saveAll(airportEntities);

    }

}
