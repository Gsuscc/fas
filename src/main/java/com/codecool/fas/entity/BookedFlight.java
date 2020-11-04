package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime bookedAt;

    private Integer passengers;

    private String fromAirport;
    private String toAirport;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonBackReference
    private UserInfo user;

    @JsonManagedReference
    @OneToMany(mappedBy = "bookedFlight", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<BookedTicket> tickets;

}
