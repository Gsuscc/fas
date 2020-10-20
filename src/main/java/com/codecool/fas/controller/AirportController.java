package com.codecool.fas.controller;


import com.codecool.fas.dao.interfaces.AirportDao;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.http.Response;
import com.codecool.fas.model.http.ResponseData;
import com.codecool.fas.model.http.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/airport")
public class AirportController {

    AirportDao airportDao;

    @Autowired
    public AirportController(    @Qualifier("AirportJpa")
                                             AirportDao airportDao) {
        this.airportDao = airportDao;
    }

    @GetMapping("/query")
    public Response<Airport> getAirports(@RequestParam String substring) {
        Response<Airport> response;
        try {
            List<Airport> airports = airportDao.getAirportsBySubstring(substring);
            response = new ResponseData<>(airports);
        } catch (NoSuchElementException e) {
            response = new ResponseError<>("No Such Element: " + substring);
        }
        return response;
    };

}
