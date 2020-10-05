package com.codecool.fas.dao.implementations.json;

import com.codecool.fas.dao.interfaces.AirportDao;
import com.codecool.fas.memory.Database;
import com.codecool.fas.model.Airport;
import com.codecool.fas.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportJson implements AirportDao {

    Database database;

    public AirportJson(Database database) {
        this.database = database;
    }

    @Override
    public List<Airport> getAirportsBySubstring(String subString) {
        return database.getAirports()
                .stream()
                .filter(
                        airport -> airport.getLabel().toLowerCase().contains(subString.toLowerCase().subSequence(0, subString.length()))
                        )
                .collect(Collectors.toList());
    }
}
