package com.codecool.fas.repository;

import com.codecool.fas.entity.Airport;
import com.codecool.fas.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("SELECT f from Flight f " +
            "WHERE f.fromAirport.code = :fromCode " +
            "AND f.toAirport.code = :toCode " +
            "AND date(f.departure)= :tripDate")
    List<Flight> findFlightsOneWay(
            String fromCode,
            String toCode,
            LocalDate tripDate
    );

    @Query("SELECT f from Flight f where f.fromAirport = ?1 AND f.toAirport = ?2 AND date(f.departure)<= ?3 AND date(f.departure) > ?4 ORDER BY f.touristPrice")
    List<Flight> findCheapestFlight(Airport fromAirport,Airport toAirport, LocalDate maxDate, LocalDate today);
}
