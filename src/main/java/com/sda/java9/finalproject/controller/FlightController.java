package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController @RequestMapping("/flights") @RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> findAll(){
        return flightService.findAll();
    }

    // TODO: method that returns the list with filtered flights but need to pass some request params or path variables lets see
    @GetMapping("/filter")
    public List<FlightDTO> findFilteredFlights(@RequestParam Long departureAirportId, @RequestParam Long arrivalAirportId, @RequestParam Date departureDate, @RequestParam Date arrivalDate){
        return flightService.findFilteredFlights(departureAirportId, arrivalAirportId, departureDate, arrivalDate);
    }

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable("id") Long id){
        return flightService.findById(id);
    }

    @PostMapping
    public FlightDTO save(FlightDTO flightDTO){
        flightService.save(flightDTO);
        return flightDTO;
    }

    @PutMapping
    public FlightDTO update(FlightDTO flightDTO){
        flightService.save(flightDTO);
        return flightDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteById(Long id){
        return flightService.deleteById(id);
    }
}
