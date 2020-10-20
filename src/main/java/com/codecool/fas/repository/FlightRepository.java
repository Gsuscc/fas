package com.codecool.fas.repository;

import com.codecool.fas.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("SELECT f from Flight f where f.fromAirport.code = ?1 AND f.toAirport.code = ?2 AND date(f.departure)= ?3")
    List<Flight> findFlightsOneWay(String fromCode, String toCode, LocalDate tripDate);
}
