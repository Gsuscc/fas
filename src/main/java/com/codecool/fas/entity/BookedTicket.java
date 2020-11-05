package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BookedTicket {

    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private BookedFlight bookedFlight;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Flight flight;

}