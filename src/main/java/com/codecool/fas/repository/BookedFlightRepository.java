package com.codecool.fas.repository;

import com.codecool.fas.entity.BookedFlight;
import com.codecool.fas.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedFlightRepository extends JpaRepository<BookedFlight, Long> {

    List<BookedFlight> findAllByUserIs(UserInfo user);
}
