package com.sda.java9.finalproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) @JoinColumn(name = "flight_id") @JsonBackReference
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private Baggage baggage;

    private boolean checkedIn;

}
