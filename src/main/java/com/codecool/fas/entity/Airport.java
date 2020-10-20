package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonManagedReference
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
    @JsonBackReference
    private Set<Flight> fromAirports;

    @Singular()
    @JsonBackReference
    @OneToMany(mappedBy = "toAirport", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Flight> toAirports;

    @Column(nullable = false)
    private String countryCode;

}
