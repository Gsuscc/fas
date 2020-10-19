package com.codecool.fas.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Column(nullable = false, unique = true, length = 3)
    private String code;
    @ManyToOne
    private City city;
    @Column(nullable = false)
    private String airportName;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @Singular()
    @OneToMany(mappedBy = "fromAirport", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Flight> fromAirports;
    @Singular()
    @OneToMany(mappedBy = "toAirport", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Flight> toAirports;

}
