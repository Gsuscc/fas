package com.codecool.fas.repository;

import com.codecool.fas.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,Long> {
}
