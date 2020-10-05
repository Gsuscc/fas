package com.codecool.fas.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Flight {

    private String name;
    private String fromCode;
    private String toCode;
    private UUID code;
    private Airline airline;
    private String aircraft;
    private Double distance;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private Double touristPrice;
    private Double businessPrice;

    public Flight() {
    }

//    public Flight(Airport flyFrom, Airport landingTo, Airline airline, LocalDateTime departure) {
//        this.name = flyFrom.getLabel() + " - " + landingTo.getLabel();
//        this.fromCode = flyFrom.getCode();
//        this.toCode = landingTo.getCode();
//        this.code = UUID.randomUUID();
//        this.airline = airline;
//        double distance = DistanceCalculator.calculate(
//                flyFrom.getLatitude(),
//                landingTo.getLatitude(),
//                flyFrom.getLongitude(),
//                landingTo.getLongitude()
//        );
//        this.distance = distance;
//        this.departure = departure;
//        this.arrival = departure.plusHours((long) Math.floor(Math.random()*3) + 1);
//        this.touristPrice = Math.random() * (distance / 2) + 20;
//        this.businessPrice = this.touristPrice * (Math.random() / 5 + 2.1);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public Double getTouristPrice() {
        return touristPrice;
    }

    public void setTouristPrice(Double touristPrice) {
        this.touristPrice = touristPrice;
    }

    public Double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Double businessPrice) {
        this.businessPrice = businessPrice;
    }

}
