package com.sda.java9.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter @Setter
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    @ManyToOne
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private Baggage baggage;

    private boolean checkedIn;

}
