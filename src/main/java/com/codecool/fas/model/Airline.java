package com.codecool.fas.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Airline {
    private String name;
    private String code;
    private URL logo;

    public URL getLogo() {
        return logo;
    }

    public void setLogo(String logo) throws MalformedURLException {
        this.logo = new URL(logo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
