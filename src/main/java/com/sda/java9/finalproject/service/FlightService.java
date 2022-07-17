package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.FlightDAO;
import com.sda.java9.finalproject.dto.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void save(FlightDTO flight) {
        if (!flight.isBiDirectional()) {
            flight.setReturnDate(null);
        }
        flightDAO.save(flight);
    }

    public String deleteById(Long id) {
        flightDAO.deleteById(id);
        return String.format("Flight with number %d deleted successfully.", id);
    }

    public List<FlightDTO> findFlights(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate) {
        return flightDAO.findFlightsBySuperQuery(departureAirportId, arrivalAirportId, departureDate, returnDate)
                .stream().filter(f -> f.getCapacity() >
                        flightDAO.countOfPassengers(departureAirportId, arrivalAirportId, departureDate, returnDate))
                .collect(Collectors.toList());
    }
}
