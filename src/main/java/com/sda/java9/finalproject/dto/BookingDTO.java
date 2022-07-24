package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.entity.Baggage;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter @Setter
public class BookingDTO {
    private Long id;
    private UUID uuid;
    private Set<PassengerDTO> passengers;
    private FlightDTO flight;
    private Baggage baggage;
    private boolean checkedIn;
    private AppUserDTO user;
}
