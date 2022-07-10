package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class FlightDAO implements GenericDAO<Flight> {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    // TODO: method that returns the result set from the native query.
    public List<Flight> getResultFromNativeQuery(String departureAirportId, String arrivalAirportId, String departureDate){
        return flightRepository.fullSearchFlights(departureAirportId, arrivalAirportId, departureDate);
    }
}
