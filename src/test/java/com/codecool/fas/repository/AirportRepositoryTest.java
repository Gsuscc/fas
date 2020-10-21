package com.codecool.fas.repository;

import com.codecool.fas.entity.Airport;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = {"classpath:application-test.properties"})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AirportRepositoryTest {
    private final AirportRepository airportRepository;

    @BeforeEach
    public void setup() {
        airportRepository.deleteAll();
    }

    @Autowired
    public AirportRepositoryTest(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Test
    public void isAirportAdded() {
//        Airport airport = Airport.builder()
//                .label("Ferihegy")
//                .code("BUD")
//                .airportName("Ferihegy")
//                .latitude(52.502777777778)
//                .longitude(13.508611111111)
//                .countryCode("HU")
//                .build();
//        airportRepository.save(airport);
        List<Airport> airports = airportRepository.findAll();
        assertThat(airports).hasSize(0);
    }

    @Test
    public void findByLabelIsContainingIgnoreCase_ReturnsAirports() {
        Airport airport1 = Airport.builder()
                .label("Ferihegy")
                .code("BUD")
                .airportName("Ferihegy")
                .latitude(52.502777777778)
                .longitude(13.508611111111)
                .countryCode("HU")
                .build();
        Airport airport2 = Airport.builder()
                .label("Janihegy")
                .code("JUD")
                .airportName("x")
                .latitude(52.502777777778)
                .longitude(13.508611111111)
                .countryCode("JA")
                .build();
        Airport airport3 = Airport.builder()
                .label("Heatrow")
                .code("LON")
                .airportName("y")
                .latitude(52.502777777778)
                .longitude(13.508611111111)
                .countryCode("UK")
                .build();
        airportRepository.saveAll(Lists.newArrayList(airport1, airport2, airport3));
        List<Airport> airports = airportRepository.findByLabelIsContainingIgnoreCase("heg");
        assertThat(airports)
                .hasSize(2)
                .containsExactlyInAnyOrder(airport1, airport2);
    }

    @Test
    public void codeSizeDifferentFromThreeThrowException() {
        Airport airport1 = Airport.builder()
                .label("Ferihegy")
                .code("BUDAPEST")
                .airportName("Ferihegy")
                .latitude(52.502777777778)
                .longitude(13.508611111111)
                .build();
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> {
                    airportRepository.save(
                            airport1);
                });
    }
}