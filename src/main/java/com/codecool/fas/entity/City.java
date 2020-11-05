package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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
    private URL cityImage;
    @Column(nullable = false)
    private String countryName;

    @ManyToOne
    @JsonBackReference
    private UserInfo user;

    @Singular()
    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Airport> airports;

    @OneToMany(mappedBy = "city", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private List<UserCity> cities;


}
