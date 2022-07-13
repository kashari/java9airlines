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

    public List<Flight> getResultFromNativeQuery(String departureAirportId, String arrivalAirportId, String departureDate){
        return flightRepository.oneDirectionalFlights(departureAirportId, arrivalAirportId, departureDate);
    }

    public List<Flight> findFlightsBySuperQuery(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate) {
        return flightRepository.biDirectionalFlights(departureAirportId, arrivalAirportId, departureDate, returnDate);
    }
}
