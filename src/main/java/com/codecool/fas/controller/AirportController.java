package com.codecool.fas.controller;


import com.codecool.fas.dao.interfaces.AirlineDao;
import com.codecool.fas.dao.interfaces.AirportDao;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/airport")
public class AirportController {

    AirportDao airportJson;

    @Autowired
    public AirportController(AirportDao airportJson) {
        this.airportJson = airportJson;
    }

    @GetMapping("/query")
    public Response<Airport> getAirports(@RequestParam String substring) {
        Response<Airport> response = new Response<>();
        try {
            List<Airport> airports = airportJson.getAirportsBySubstring(substring);
            response.setStatus("success");
            response.setValues(airports);
        } catch (NoSuchElementException e) {
            response.setStatus("error");
            response.setErrorMessage("Not found");
        }
        return response;
    };

}
