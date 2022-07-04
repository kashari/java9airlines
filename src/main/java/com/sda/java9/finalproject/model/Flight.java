package com.sda.java9.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity @Getter @Setter
public class Flight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;

    private Date departureDate;
    private Date arrivalDate;

    @ManyToMany
    private Set<Passenger> passengers;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    private double flightPrice;
}
