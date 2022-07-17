package com.sda.java9.finalproject.dto;

import com.sda.java9.finalproject.model.FlightClass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class FlightDTO {
    private Long id;
    private int capacity;
    private boolean biDirectional;
    private AirportDTO departureAirport;
    private AirportDTO arrivalAirport;
    private Date departureDate;
    private Date returnDate;
    private FlightClass flightClass;
    private double flightPrice;
}
