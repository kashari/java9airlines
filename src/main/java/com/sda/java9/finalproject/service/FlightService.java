package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.FlightDAO;
import com.sda.java9.finalproject.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {

    private final FlightDAO flightDAO;

    public List<Flight> findAll() {
        return flightDAO.findAll();
    }

    public Flight findById(Long id) {
        return flightDAO.findById(id);
    }

    public void save(Flight flight) {
        flightDAO.save(flight);
    }

    public String deleteById(Long id) {
        flightDAO.deleteById(id);
        return String.format("Flight with number %d deleted successfully.", id);
    }

    // basically if a flight capacity is full cannot add more bookings so don't get this record at all from the database
    public List<Flight> findFilteredFlights(String departureAirportId, String arrivalAirportId, String departureDate) {
        return flightDAO.getResultFromNativeQuery(departureAirportId, arrivalAirportId, departureDate)
                .stream().filter(flightDTO -> flightDTO.getCapacity() > flightDTO.getBookings().size())
                .collect(Collectors.toList());
    }
}
