package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.model.Booking;
import com.sda.java9.finalproject.model.FlightClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class FlightDTO {

    private Long id;
    private int capacity;
    private boolean biDirectional;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Set<Booking> bookings = new HashSet<>();
    private Date departureDate;
    private Date returnDate;
    private FlightClass flightClass;
    private double flightPrice;

}
