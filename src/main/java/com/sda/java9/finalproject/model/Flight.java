package com.sda.java9.finalproject.model;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

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

    @ManyToMany(mappedBy = "flights", cascade = CascadeType.ALL) @JsonIgnore
    private Set<Passenger> passengers;

    private Date departureDate;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    private double flightPrice;
}
