package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.AirportDTO;
import com.sda.java9.finalproject.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/airports") @RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> findAll(){
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    public AirportDTO findById(Long id){
        return airportService.findById(id);
    }

    @PostMapping
    public AirportDTO save(@RequestBody AirportDTO airportDTO){
        airportService.save(airportDTO);
        return airportDTO;
    }

    @PutMapping
    public AirportDTO update(@RequestBody AirportDTO airportDTO){
        airportService.save(airportDTO);
        return airportDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return airportService.deleteById(id);
    }
}
