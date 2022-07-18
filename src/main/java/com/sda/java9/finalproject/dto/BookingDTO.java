package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.Baggage;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class BookingDTO {
    private Long id;
    private Set<PassengerDTO> passengers;
    private FlightDTO flight;
    private Baggage baggage;
    private boolean checkedIn;
}