package com.codecool.fas.entity;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String code;
    @Column(nullable = false)
    private URL logo;
    @OneToMany(mappedBy = "airline")
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<Flight> flights;
}
