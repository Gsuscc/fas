package com.codecool.fas.controller;

import com.codecool.fas.dao.interfaces.FlightDao;
import com.codecool.fas.model.Flight;
import com.codecool.fas.model.FlightQuery;
import com.codecool.fas.model.http.Response;
import com.codecool.fas.model.http.ResponseData;
import com.codecool.fas.model.http.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/flight")
public class FlightController {

    FlightDao flightJson;

    @Autowired
    public FlightController(FlightDao flightJson) {
        this.flightJson = flightJson;
    }


    @GetMapping(
            value = "/query",
            params = {
                    "fromCode",
                    "toCode",
                    "tripDate",
                    "person"
            })
    public Response<FlightQuery> getAirports(
            @RequestParam String fromCode,
            @RequestParam String toCode,
            @RequestParam String tripDate,
            @RequestParam String person
    ) {
        Response<FlightQuery> response;
        List<FlightQuery> flights;
        try {
            flights = flightJson.getFlights(fromCode, toCode, getLocalDate(tripDate), Integer.valueOf(person));
            response = new ResponseData<>(flights);
        } catch (Exception e) {
            response = new ResponseError<>("error!!!");
        }
        return response;
    }

    @GetMapping(
            value = "/query",
            params = {
                    "fromCode",
                    "toCode",
                    "tripDate",
                    "person",
                    "timeFrom",
                    "timeTo",
                    "priceFrom",
                    "priceTo",
                    "airlineCode"
            })
    public Response<FlightQuery> getAirports(
            @RequestParam String fromCode,
            @RequestParam String toCode,
            @RequestParam String tripDate,
            @RequestParam String person,
            @RequestParam String timeFrom,
            @RequestParam String timeTo,
            @RequestParam String priceFrom,
            @RequestParam String priceTo,
            @RequestParam String[] airlineCode
    ) {
        Response<FlightQuery> response;
        List<FlightQuery> flights;
        try {
            flights = flightJson.getFlights(fromCode, toCode, getLocalDate(tripDate), Integer.valueOf(person), getLocalTime(timeFrom), getLocalTime(timeTo), Arrays.asList(airlineCode), Double.parseDouble(priceFrom), Double.parseDouble(priceTo));
            response = new ResponseData<>(flights);
        } catch (Exception e) {
            response = new ResponseError<>("error!!!");
        }
        return response;
    }

    @GetMapping(
            value = "/query",
            params = {
                    "fromCode",
                    "toCode",
                    "tripDate",
                    "returnDate",
                    "person"
            })
    public Response<FlightQuery> getAirports(
            @RequestParam String fromCode,
            @RequestParam String toCode,
            @RequestParam String tripDate,
            @RequestParam String returnDate,
            @RequestParam String person
    ) {
        Response<FlightQuery> response;
        List<FlightQuery> flights;
        try {
            LocalDate trip = getLocalDate(tripDate);
            flights = flightJson.getFlights(fromCode, toCode, trip, getLocalDate(returnDate), Integer.valueOf(person));
            response = new ResponseData<>(flights);
        } catch (Exception e) {
            response = new ResponseError<>("error!!!");
        }
        return response;
    }

    @GetMapping(
            value = "/query",
            params = {
                    "fromCode",
                    "toCode",
                    "tripDate",
                    "returnDate",
                    "person",
                    "timeFrom",
                    "timeTo",
                    "priceFrom",
                    "priceTo",
                    "airlineCode"
            })
    public Response<FlightQuery> getAirports(
            @RequestParam String fromCode,
            @RequestParam String toCode,
            @RequestParam String tripDate,
            @RequestParam String returnDate,
            @RequestParam String person,
            @RequestParam String timeFrom,
            @RequestParam String timeTo,
            @RequestParam String priceFrom,
            @RequestParam String priceTo,
            @RequestParam String[] airlineCode
    ) {
        Response<FlightQuery> response;
        List<FlightQuery> flights;
        try {
            flights = flightJson.getFlights(fromCode, toCode, getLocalDate(tripDate), getLocalDate(returnDate), Integer.valueOf(person), getLocalTime(timeFrom), getLocalTime(timeTo), Arrays.asList(airlineCode), Double.parseDouble(priceFrom), Double.parseDouble(priceTo));
            response = new ResponseData<>(flights);
        } catch (Exception e) {
            response = new ResponseError<>("error!!!");
        }
        return response;
    }

    private LocalDate getLocalDate(String date) {
        return LocalDate.of(
                Integer.parseInt(date.split("-")[0]),
                Integer.parseInt(date.split("-")[1]),
                Integer.parseInt(date.split("-")[2]));
    }

    private LocalTime getLocalTime(String date) {
        return LocalTime.of(
                Integer.parseInt(date.split(":")[0]),
                Integer.parseInt(date.split(":")[1]),
                Integer.parseInt(date.split(":")[2]));
    }


}
