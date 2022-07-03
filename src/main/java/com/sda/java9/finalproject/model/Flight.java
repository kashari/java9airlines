package com.sda.java9.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter
public class Flight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;

    private Date departureDate;
    private Date arrivalDate;

    @OneToMany(mappedBy = "flight")
    private List<Passenger> passengers;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    private double flightPrice;
}
