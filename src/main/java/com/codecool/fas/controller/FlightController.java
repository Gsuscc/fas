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


    @GetMapping("/query")
    public Response<FlightQuery> getAirports(@RequestParam String fromCode, @RequestParam String toCode, @RequestParam String tripDate, @RequestParam String person) {
        Response<FlightQuery> response;
        System.out.println(fromCode);
        System.out.println(toCode);
        System.out.println(tripDate);
        System.out.println(person);
        try {
            LocalDate trip = LocalDate.of(
                            Integer.parseInt(tripDate.split("-")[0]),
                            Integer.parseInt(tripDate.split("-")[1]),
                            Integer.parseInt(tripDate.split("-")[2]));

            List<FlightQuery> flights = flightJson.getFlights(fromCode, toCode, trip, Integer.valueOf(person));
            response = new ResponseData<>(flights);
        } catch (Exception e) {
            response = new ResponseError<>("error!!!" );
        }
        return response;
    };

}
