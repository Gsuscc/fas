package com.codecool.fas.entity;

import com.codecool.fas.entity.Airline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private String name;
    @ManyToOne
    private Airline airline;
    @ManyToOne
    private Airport fromAirport;
    @ManyToOne
    private Airport toAirport;
    @Transient
    private String aircraft;
    @Column(nullable = false)
    private Double distance;
    @Column(nullable = false)
    private LocalDateTime departure;
    @Column(nullable = false)
    private LocalDateTime arrival;
    @Column(nullable = false)
    private Double touristPrice;
    @Column(nullable = false)
    private Double businessPrice;
    @Column(nullable = false)
    private LocalTime travelTime;
}
