package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.PassengerDTO;
import com.sda.java9.finalproject.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/passengers") @RequiredArgsConstructor @CrossOrigin("http://localhost:4200")
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public List<PassengerDTO> findAll(){
        return passengerService.findAll();
    }
    @GetMapping("/{id}")
    public PassengerDTO findById(@PathVariable("id") Long id){
        return passengerService.findById(id);
    }
    @PostMapping
    public PassengerDTO save(@RequestBody PassengerDTO passenger){
        passengerService.save(passenger);
        return passenger;
    }
    @PutMapping
    public PassengerDTO update(@RequestBody PassengerDTO passenger){
        passengerService.save(passenger);
        return passenger;
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return passengerService.deleteById(id);
    }


}
