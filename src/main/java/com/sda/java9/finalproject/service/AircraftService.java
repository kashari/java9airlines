package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AircraftDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AircraftService {

    private final AircraftDAO aircraftDAO;
    //implement all methods here
}
