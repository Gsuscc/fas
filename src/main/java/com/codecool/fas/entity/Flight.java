package com.codecool.fas.entity;

import com.codecool.fas.entity.Airline;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Airline airline;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Airport fromAirport;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
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

    @OneToMany(mappedBy = "toFlight", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private List<BookedFlight> bookedToFlightList;

    @OneToMany(mappedBy = "returnFlight", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private List<BookedFlight> bookedReturnFlightList;
}
