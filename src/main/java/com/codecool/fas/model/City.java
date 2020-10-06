package com.codecool.fas.model;

import java.net.URL;

public class City {
    String cityName;
    URL cityImage;
    String countryName;

    public City(String cityName, URL cityImage, String countryName) {
        this.cityName = cityName;
        this.cityImage = cityImage;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public URL getCityImage() {
        return cityImage;
    }

    public void setCityImage(URL cityImage) {
        this.cityImage = cityImage;
    }
}
