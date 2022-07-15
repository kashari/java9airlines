package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.Baggage;
import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.model.Passenger;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingDTO {
    private Long id;
    private Passenger passenger;
    private Flight flight;
    private Baggage baggage;
    private boolean checkedIn;

}
