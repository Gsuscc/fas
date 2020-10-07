package com.codecool.fas.controller;


import com.codecool.fas.dao.interfaces.TripAdviseDao;
import com.codecool.fas.model.TripAdvise;
import com.codecool.fas.model.http.Response;
import com.codecool.fas.model.http.ResponseData;
import com.codecool.fas.model.http.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/advisor")
public class TripAdviseController {

    private final TripAdviseDao tripAdviseJson;


    @Autowired
    public TripAdviseController(TripAdviseDao tripAdviseJson) {
        this.tripAdviseJson = tripAdviseJson;
    }

    @GetMapping
    public Response<TripAdvise> getAdvises(@RequestParam String country) {
        Response<TripAdvise> response;
        try {
            response = new ResponseData<>(tripAdviseJson.getAdvices(country)
            );
        } catch (Exception e) {
            response = new ResponseError<>(e.getMessage());
        }
        return response;
    }
}
