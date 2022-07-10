package com.sda.java9.finalproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor
public class Flight {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;

    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;

    @JsonIgnore @OneToMany(mappedBy = "flight", fetch=FetchType.LAZY) @JsonManagedReference
    private Set<Booking> bookings = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;

    private double flightPrice;

}
