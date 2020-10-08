package com.codecool.fas.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private LocalTime travelTime;

    public Flight(String name, String fromCode, String toCode, UUID code, Airline airline, String aircraft, Double distance, LocalDateTime departure, LocalDateTime arrival, Double touristPrice, Double businessPrice) {
        this.name = name;
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.code = code;
        this.airline = airline;
        this.aircraft = aircraft;
        this.distance = distance;
        this.departure = departure;
        this.arrival = arrival;
        this.touristPrice = touristPrice;
        this.businessPrice = businessPrice;
        this.travelTime = arrival.toLocalTime()
                .minusHours(departure.getHour())
                .minusMinutes(departure.getMinute())
                .minusSeconds(departure.getSecond());
    }

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }
}
