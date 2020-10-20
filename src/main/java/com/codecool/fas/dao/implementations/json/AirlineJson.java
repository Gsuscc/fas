//package com.codecool.fas.dao.implementations.json;
//
//import com.codecool.fas.dao.interfaces.AirlineDao;
//import com.codecool.fas.memory.Database;
//import com.codecool.fas.model.Airline;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//@Component
//public class AirlineJson implements AirlineDao {
//
//    Database database;
//
//    public AirlineJson(Database database) {
//        this.database = database;
//    }
//
//    @Override
//    public Airline getAirlineByCode(String airlineCode) throws NoSuchElementException {
//        return database.getAirlines().
//                stream().
//                filter(airline -> airlineCode.equals(airline.getCode())).
//                findFirst().
//                orElseThrow();
//    }
//
//    @Override
//    public List<Airline> getAirlines() {
//        return database.getAirlines();
//    }
//}
