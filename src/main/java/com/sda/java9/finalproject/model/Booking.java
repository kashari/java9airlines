package com.sda.java9.finalproject.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class Booking {

    // TODO: need this as a wrapper class in the end to put all components together and make a booking
    // needs to have a flight the flight passengers as a list and the total cost for the passengers that make a booking at
    // the same time

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Passenger passenger;

    @OneToOne
    private Flight flight;

}
