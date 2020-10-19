package com.codecool.fas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Airport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String label;
    @Column(nullable = false, unique = true)
    private String code;
    @ManyToOne
    @Column(nullable = false)
    private City city;
    @Column(nullable = false)
    private String airportName;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @OneToMany(mappedBy = "fromAirport")
    private Set<Flight> fromAirports;
    @OneToMany(mappedBy = "toAirport")
    private Set<Flight> toAirports;

}
