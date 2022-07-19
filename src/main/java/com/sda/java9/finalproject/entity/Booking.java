package com.sda.java9.finalproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter @Setter
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    @ManyToOne
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private Baggage baggage;

    @ManyToOne
    private AppUser appUser;

    private boolean checkedIn;

}
