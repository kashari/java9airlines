package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.AircraftDTO;
import com.sda.java9.finalproject.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/aircrafts") @RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    // implement all mappings here

    @GetMapping
    public List<AircraftDTO> findAll(){
        return aircraftService.findAll();
    }

    @GetMapping("/{id}")
    public AircraftDTO findById(@PathVariable("id") Long id){
        return aircraftService.findById(id);
    }

    @PostMapping
    public AircraftDTO save(@RequestBody AircraftDTO aircraftDTO){
        aircraftService.save(aircraftDTO);
        return aircraftDTO;
    }

    @PutMapping
    public AircraftDTO update(@RequestBody AircraftDTO aircraftDTO){
        aircraftService.save(aircraftDTO);
        return aircraftDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return aircraftService.deleteById(id);
    }
}
