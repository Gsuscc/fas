package com.codecool.fas.memory;

import com.codecool.fas.entity.Airline;
import com.codecool.fas.entity.Airport;
import com.codecool.fas.entity.City;
import com.codecool.fas.entity.Flight;
import com.codecool.fas.repository.FlightRepository;
import com.codecool.fas.util.DistanceCalculator;
import com.codecool.fas.util.FileHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Scope("singleton")
public class JsonToPSQL {

    public static final int MAX_DISTANCE_CAPACITY = 5300;
    private List<com.codecool.fas.model.Airline> airlines;
    private List<com.codecool.fas.model.Airport> airports;
    private List<com.codecool.fas.model.City> cities;
    private final List<Airline> airlineEntities = new ArrayList<>();
    private final List<Airport> airportEntities = new ArrayList<>();
    private final List<City> cityEntities = new ArrayList<>();
    private final List<Flight> flightEntities = new ArrayList<>();


    private final FlightRepository flightRepository;
    @Autowired
    public JsonToPSQL(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

        // Uncomment this to fill DB with values
        // fillDatabase();
    }

    private void fillDatabase() {
        readJsonFiles();
        writeToDB();
    }

    private void readJsonFiles() {
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
    }

    public void writeToDB() {
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

        int DAYS = 100;
        LocalDateTime today = LocalDate.now().atStartOfDay();
        for (int i = 0; i < DAYS; i++) {
            for (Airport airportFrom : airportEntities) {
                for (Airport airportTo : airportEntities) {
                    if (airportFrom.getCity().getCityName().equals(airportTo.getCity().getCityName())) continue;
                    double distance = DistanceCalculator.calculate(
                            airportFrom.getLatitude(),
                            airportTo.getLatitude(),
                            airportFrom.getLongitude(),
                            airportTo.getLongitude());
                    if (distance > MAX_DISTANCE_CAPACITY) continue;
                    for (int j = 0; j < Math.floor(Math.random() * 4) + 1; j++) {
                        Airline airline = airlineEntities.get((int) Math.floor(Math.random() * airlines.size()));

                        LocalDateTime departure = today.plusMinutes((long) Math.floor(Math.random() * 1440));
                        LocalTime traveltime = LocalTime.MIDNIGHT.plusMinutes((long) (distance / 13 + 30));
                        LocalDateTime arrival = departure.plusMinutes(traveltime.getMinute()).plusHours(traveltime.getHour());
                        double touristPrice = Math.random() * (distance / 2) + 20;
                        double businessPrice = touristPrice * (Math.random() / 5 + 2.1);
                        Flight flight = Flight.builder()
                                .fromAirport(airportFrom)
                                .toAirport(airportTo)
                                .airline(airline)
                                .name(airportFrom.getLabel() + " - " + airportTo.getLabel())
                                .distance(distance)
                                .departure(departure)
                                .arrival(arrival)
                                .touristPrice(touristPrice)
                                .businessPrice(businessPrice)
                                .travelTime(traveltime)
                                .build();
                        flightEntities.add(flight);
                    }

                }
            }
            today = today.plusDays(1);
        }

        flightRepository.saveAll(flightEntities);
    }


}
