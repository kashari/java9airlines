package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController @RequestMapping("/flights") @RequiredArgsConstructor @CrossOrigin(value = "http://localhost:4200")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> findAll(){
        return flightService.findAll();
    }

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable("id") Long id){
        return flightService.findById(id);
    }

    @PostMapping
    public FlightDTO save(@RequestBody FlightDTO flight){
        flightService.save(flight);
        return flight;
    }

    @PutMapping
    public FlightDTO update(@RequestBody FlightDTO flight){
        flightService.save(flight);
        return flight;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return flightService.deleteById(id);
    }
}
