package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.model.Passenger;
import com.sda.java9.finalproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/passengers") @RequiredArgsConstructor @CrossOrigin("http://localhost:4200")
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public List<Passenger> findAll(){
        return passengerService.findAll();
    }
    @GetMapping("/{id}")
    public Passenger findById(@PathVariable("id") Long id){
        return passengerService.findById(id);
    }
    @PostMapping
    public Passenger save(@RequestBody Passenger passenger){
        passengerService.save(passenger);
        return passenger;
    }
    @PutMapping
    public Passenger update(@RequestBody Passenger passenger){
        passengerService.save(passenger);
        return passenger;
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return passengerService.deleteById(id);
    }


}
