package com.sda.java9.finalproject.model;


import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter @Setter
public class Passenger {

    // TODO: needs refactoring in both this entity and the Passenger one about the relationship between them

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String passportCode;

    @ManyToMany @JoinTable(
            name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    @JsonIgnore
    private Set<Flight> flights;

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;
}
