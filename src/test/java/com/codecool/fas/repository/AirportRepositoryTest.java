package com.codecool.fas.repository;

import com.codecool.fas.entity.Airport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@DataJpaTest
//@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AirportRepositoryTest {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportRepositoryTest(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Test
    public void isAirportAdded() {
        Airport airport = Airport.builder()
                .label("Ferihegy")
                .code("BUD")
                .airportName("Ferihegy")
                .latitude(52.502777777778)
                .longitude(13.508611111111)
                .build();
        airportRepository.save(airport);
        List<Airport> airports = airportRepository.findAll();
        assertThat(airports).hasSize(1);
    }
}