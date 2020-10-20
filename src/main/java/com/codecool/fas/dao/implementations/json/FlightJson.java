//package com.codecool.fas.dao.implementations.json;
//
//import com.codecool.fas.dao.interfaces.FlightDao;
//import com.codecool.fas.memory.Database;
//import com.codecool.fas.model.Flight;
//import com.codecool.fas.model.FlightQuery;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class FlightJson implements FlightDao {
//
//    Database database;
//    private final List<Flight> flights;
//
//    public FlightJson(Database database) {
//        this.database = database;
//        flights = database.getFlights();
//    }
//
//
//    private List<Flight> getFlightsForDay(String departureCode, String arriveCode, LocalDate tripDate) {
//        return flights.stream().filter(flight ->
//            flight.getFromCode().equals(departureCode) &&
//                    flight.getToCode().equals(arriveCode) &&
//                    flight.getDeparture().toLocalDate().equals(tripDate)
//        ).collect(Collectors.toList());
//    }
//
//    private List<Flight> getFlightsFiltered(List<Flight> flightList, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
//        return flightList.stream().filter(flight ->
//                airlineCode.contains(flight.getAirline().getCode())
//                && flight.getTouristPrice() < priceTo
//                && flight.getTouristPrice() > priceFrom
//                && flight.getDeparture().toLocalTime().isAfter(timeFrom)
//                && flight.getDeparture().toLocalTime().isBefore(timeTo)
//        ).collect(Collectors.toList());
//    }
//
//    private boolean isValidDateTime(LocalDateTime arriveTo, LocalDateTime departureBack) {
//        return departureBack.isAfter(arriveTo.plusHours(1));
//    }
//
//
//    @Override
//    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person) {
//        List<FlightQuery> result = new ArrayList<>();
//        List<Flight> flightList = getFlightsForDay(departureCode, arriveCode, tripDate);
//        flightList.forEach(flight -> result.add(new FlightQuery(flight, null, person)));
//        return result;
//    }
//
//    @Override
//    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, Integer person, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
//        List<FlightQuery> result = new ArrayList<>();
//        List<Flight> flightList = getFlightsForDay(departureCode, arriveCode, tripDate);
//        flightList = getFlightsFiltered(flightList, timeFrom, timeTo, airlineCode, priceFrom, priceTo);
//        flightList.forEach(flight -> result.add(new FlightQuery(flight, null, person)));
//        return result;
//    }
//
//    @Override
//    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person) {
//        List<FlightQuery> result = new ArrayList<>();
//        List<Flight> toFlights = getFlightsForDay(departureCode, arriveCode, tripDate);
//        List<Flight> backFlights = getFlightsForDay(arriveCode, departureCode, tripBackDate);
//        toFlights.forEach(flight -> backFlights.forEach(backFlight -> {
//            if (isValidDateTime(flight.getArrival(), backFlight.getDeparture()))
//                result.add(new FlightQuery(flight, backFlight, person));
//        }));
//        return result;
//    }
//
//    @Override
//    public List<FlightQuery> getFlights(String departureCode, String arriveCode, LocalDate tripDate, LocalDate tripBackDate, Integer person, LocalTime timeFrom, LocalTime timeTo, List<String> airlineCode, Double priceFrom, Double priceTo) {
//        List<FlightQuery> result = new ArrayList<>();
//        List<Flight> toFlights = getFlightsForDay(departureCode, arriveCode, tripDate);
//        List<Flight> backFlights = getFlightsForDay(arriveCode, departureCode, tripBackDate);
//        toFlights = getFlightsFiltered(toFlights, timeFrom, timeTo, airlineCode, priceFrom, priceTo);
//        backFlights = getFlightsFiltered(backFlights, timeFrom, timeTo, airlineCode, priceFrom, priceTo);
//        List<Flight> finalBackFlights = backFlights;
//        toFlights.forEach(flight -> finalBackFlights.forEach(backFlight -> {
//            if (isValidDateTime(flight.getArrival(), backFlight.getDeparture()))
//                result.add(new FlightQuery(flight, backFlight, person));
//        }));
//        return result;
//    }
//}
