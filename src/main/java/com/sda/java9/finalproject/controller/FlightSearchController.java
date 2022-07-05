package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/api")
public class FlightSearchController {

    private final FlightService flightService;

    @GetMapping("/filter")
    public List<FlightDTO> findFilteredFlights(@RequestParam(required = false) String departureAirportId, @RequestParam(required = false) String arrivalAirportId, @RequestParam(required = false) String departureDate){
        return flightService.findFilteredFlights(departureAirportId, arrivalAirportId, departureDate);
    }
}
