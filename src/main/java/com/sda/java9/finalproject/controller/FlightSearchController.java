package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/search") @CrossOrigin("http://localhost:4200")
public class FlightSearchController {

    private final FlightService flightService;
    @GetMapping("/flights")
    public List<Flight> findFilteredFlights(@RequestParam(required = false) String departureAirportId,
                                           @RequestParam(required = false) String arrivalAirportId,
                                           @RequestParam(required = false) String departureDate,
                                           @RequestParam(required = false) String returnDate,
                                           @RequestParam(required = false) boolean isBiDirectional){
        if (isBiDirectional){
            /*
            TODO:    in front-end when displayed the flight for the return need to inverse the things
                     so basically the departure airport becomes arrival and inverse same thing applied for the dates
             */
            return flightService.findFlightsBiDirectional(departureAirportId, arrivalAirportId, departureDate, returnDate);
        }
        return flightService.findFlightsOneDirectional(departureAirportId, arrivalAirportId, departureDate);
    }
}
