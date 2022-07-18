package com.sda.java9.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter @Setter
public class Booking {

    /* TODO: logic needed here -> need to add some check-in mechanism that allows the user to check in online
             so when the user opens this endpoint can provide some unique passportCode that can enable the check-in and use java
             send mail functionality to send some kind of online booking ticket with all the details.
    */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    @ManyToOne
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private Baggage baggage;

    private boolean checkedIn;

}
