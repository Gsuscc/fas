package com.codecool.fas.repository;

import com.codecool.fas.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByLabelIsContainingIgnoreCase(String subString);

}
