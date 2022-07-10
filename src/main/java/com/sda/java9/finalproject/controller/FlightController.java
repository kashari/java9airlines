package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController @RequestMapping("/flights") @RequiredArgsConstructor @CrossOrigin("http://localhost:4200")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<Flight> findAll(){
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    public Flight findById(@PathVariable("id") Long id){
        return flightService.findById(id);
    }

    @PostMapping
    public Flight save(@RequestBody Flight flight){
        flightService.save(flight);
        return flight;
    }

    @PutMapping
    public Flight update(@RequestBody Flight flight){
        flightService.save(flight);
        return flight;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return flightService.deleteById(id);
    }
}
