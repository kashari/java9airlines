package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.model.PassengerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class PassengerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String passportCode;
    private Set<Flight> flights;
    private PassengerType passengerType;

}
