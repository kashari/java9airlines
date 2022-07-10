package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class AirportDAO implements GenericDAO<Airport> {

    private final AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Long id) {
        return airportRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }
}
