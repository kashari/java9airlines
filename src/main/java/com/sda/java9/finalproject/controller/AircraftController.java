package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/aircraft") @RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    // implement all mappings here
}
