package com.codecool.fas.controller;


import com.codecool.fas.dao.interfaces.TripAdviseDao;
import com.codecool.fas.model.TripAdvise;
import com.codecool.fas.model.http.Response;
import com.codecool.fas.model.http.ResponseData;
import com.codecool.fas.model.http.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/advisor")
public class TripAdviseController {

    private final TripAdviseDao tripAdviseDao;


    @Autowired
    public TripAdviseController(@Qualifier("tripAdviseJpa") TripAdviseDao tripAdviseDao) {
        this.tripAdviseDao = tripAdviseDao;
    }

    @GetMapping
    public Response<TripAdvise> getAdvises(@RequestParam String country) {
        Response<TripAdvise> response;
        try {
            response = new ResponseData<>(tripAdviseDao.getAdvices(country)
            );
        } catch (Exception e) {
            response = new ResponseError<>(e.getMessage());
        }
        return response;
    }
}
