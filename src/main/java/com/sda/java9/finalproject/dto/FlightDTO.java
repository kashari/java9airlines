package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.model.FlightClass;
import com.sda.java9.finalproject.model.Passenger;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter @Setter
public class FlightDTO  {

    private Long id;
    private int capacity;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Date departureDate;
    private Date arrivalDate;
    private Set<Passenger> passengers;
    private FlightClass flightClass;
    private double flightPrice;
}
