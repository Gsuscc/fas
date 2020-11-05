package com.codecool.fas.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>(Collections.singletonList("User"));

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    List<BookedFlight> bookedFlights;


//
//    // roles of the user (ADMIN, USER,..)
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> favouriteCities = new ArrayList<>();
}
