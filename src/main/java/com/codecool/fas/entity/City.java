package com.codecool.fas.entity;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class City {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String cityName;
    @Column(nullable = false)
    private URL cityImage;
    @Column(nullable = false)
    private String countryName;
    @Column(nullable = false,unique = true)
    private String countryCode;
    @Singular()
    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<Airport> airports;

}
