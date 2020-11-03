package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BookedFlight {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonManagedReference
    private UserInfo user;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Flight toFlight;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Flight returnFlight;
}
