package com.sda.java9.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Flight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    private boolean biDirectional;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    @Temporal(TemporalType.DATE)
    private Date departureDate;

    // only if the flight is bidirectional
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    private double flightPrice;

}
