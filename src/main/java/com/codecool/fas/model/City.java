package com.codecool.fas.model;

import java.net.URL;

public class City {
    String cityName;
    URL cityImage;

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
