package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.entity.PassengerType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PassengerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String passportCode;
    private PassengerType passengerType;
}
