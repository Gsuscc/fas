package com.codecool.fas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;

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
}
