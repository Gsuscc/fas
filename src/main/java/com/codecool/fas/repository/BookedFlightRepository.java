package com.codecool.fas.repository;

import com.codecool.fas.entity.BookedFlight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedFlightRepository extends JpaRepository<BookedFlight, Long> {

}
