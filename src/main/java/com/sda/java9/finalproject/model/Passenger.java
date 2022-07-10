package com.sda.java9.finalproject.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class Passenger {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String passportCode;

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;
}
