package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.FlightQuery;

import java.time.LocalDate;
import java.util.List;

public interface FlightDao {

    List<FlightQuery> getFlights(String departureCode,
                                 String arriveCode,
                                 LocalDate tripDate,
                                 Integer person
    );
    List<FlightQuery> getFlights(String departureCode,
                                 String arriveCode,
                                 LocalDate tripDate,
                                 Integer person,
                                 List<String> airlineCode,
                                 Integer priceFrom,
                                 Integer priceTo
    );
    List<FlightQuery> getFlights(String departureCode,
                                 String arriveCode,
                                 LocalDate tripDate,
                                 LocalDate tripBackDate,
                                 Integer person
    );
    List<FlightQuery> getFlights(String departureCode,
                                 String arriveCode,
                                 LocalDate tripDate,
                                 LocalDate tripBackDate,
                                 Integer person,
                                 List<String> airlineCode,
                                 Integer priceFrom,
                                 Integer priceTo
    );

}
