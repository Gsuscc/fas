package com.codecool.fas.dao.interfaces;

import com.codecool.fas.model.FlightQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
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
