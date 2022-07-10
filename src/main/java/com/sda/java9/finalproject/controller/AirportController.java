package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/airports") @RequiredArgsConstructor @CrossOrigin("http://localhost:4200")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<Airport> findAll(){
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    public Airport findById(Long id){
        return airportService.findById(id);
    }

    @PostMapping
    public Airport save(@RequestBody Airport airport){
        airportService.save(airport);
        return airport;
    }

    @PutMapping
    public Airport update(@RequestBody Airport airport){
        airportService.save(airport);
        return airport;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return airportService.deleteById(id);
    }
}
