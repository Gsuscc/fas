package com.codecool.fas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserCity {
    @Id
    @GeneratedValue
    private Long id;

    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private City city;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private UserInfo userInfo;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean notification;
}
