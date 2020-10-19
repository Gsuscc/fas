package com.codecool.fas.repository;

import com.codecool.fas.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
