package com.codecool.fas.model;

public class TripAdvise {

    private String pictureUrl;
    private String linkUrl;
    private double price;
    private String city;
    private String country;

    public TripAdvise(String pictureUrl, String linkUrl, double price, String city, String country) {
        this.pictureUrl = pictureUrl;
        this.linkUrl = linkUrl;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
