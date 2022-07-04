package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.FlightDAO;
import com.sda.java9.finalproject.dto.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {

    private final FlightDAO flightDAO;

    public List<FlightDTO> findAll() {
        return flightDAO.findAll();
    }

    public FlightDTO findById(Long id) {
        return flightDAO.findById(id);
    }

    public void save(FlightDTO flightDTO) {
        flightDAO.save(flightDTO);
    }

    public String deleteById(Long id) {
        flightDAO.deleteById(id);
        return String.format("Flight with number %d deleted successfully.", id);
    }

    // basically if a flight capacity is full cannot add more bookings so don't get this record at all from the database
    public List<FlightDTO> findFilteredFlights(Long departureAirportId, Long arrivalAirportId, Date departureDate, Date arrivalDate) {
        return flightDAO.
                getResultFromNativeQuery(departureAirportId, arrivalAirportId, departureDate, arrivalDate)
                .stream().filter(flight -> flight.getCapacity() >= flight.getPassengers().size())
                .collect(Collectors.toList());
    }
}
