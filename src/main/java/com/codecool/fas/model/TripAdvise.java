package com.codecool.fas.model;
import com.codecool.fas.entity.Airport;


public class TripAdvise {

    private String pictureUrl;
    private Airport fromAirport;
    private Airport toAirport;
    private int year;
    private int month;
    private int day;
    private String price;
    private String city;
    private String country;


    public TripAdvise(String pictureUrl, Airport fromAirport, Airport toAirport, int year, int month, int day, String price, String city, String country) {
        this.pictureUrl = pictureUrl;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.year = year;
        this.month = month;
        this.day = day;
        this.price = price;
        this.city = city;
        this.country = country;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
