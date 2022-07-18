package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.AirportDTO;
import com.sda.java9.finalproject.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController @RequestMapping("/airports") @RequiredArgsConstructor @CrossOrigin("http://localhost:4200")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> findAll(){
        return airportService.findAll();
    }

    @GetMapping("/{id}")
    public AirportDTO findById(@PathVariable("id") Long id){
        return airportService.findById(id);
    }

    @PostMapping
    public AirportDTO save(@RequestBody AirportDTO airport){
        airportService.save(airport);
        return airport;
    }

    @PutMapping
    public AirportDTO update(@RequestBody AirportDTO airport){
        airportService.save(airport);
        return airport;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id){
        return airportService.deleteById(id);
    }

    @PostMapping("/bulk-create")
    public String bulkCreate(@Valid @RequestPart(name = "file") MultipartFile file) throws IOException {
        if (Objects.nonNull(file)){
            airportService.saveAll(file.getInputStream());
            return "Airports created successfully.";
        }
        return "Something went wrong.";
    }
}
