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
        if (!flight.isBiDirectional()) {
            flight.setReturnDate(null);
        }
        flightDAO.save(flight);
    }

    public String deleteById(Long id) {
        flightDAO.deleteById(id);
        return String.format("Flight with number %d deleted successfully.", id);
    }

    public List<Flight> findFlightsOneDirectional(String departureAirportId, String arrivalAirportId, String departureDate) {
        return flightDAO.getResultFromNativeQuery(departureAirportId, arrivalAirportId, departureDate)
                .stream().filter(flight -> flight.getCapacity() > flight.getBookings().size())
                .collect(Collectors.toList());
    }

    /*
    TODO: need to add logic based in number of passengers requesting to book
          so if the bookings size adding the number of these passengers exceeds
          the flight capacity or equals it the flight should not be fetched at all
          in both of these methods + the validation if the user requests a return date but the date is null
          we should return an exception or error message ... plus the security and booking part
    */
    public List<Flight> findFlightsBiDirectional(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate) {
        return flightDAO.findFlightsBySuperQuery(departureAirportId, arrivalAirportId, departureDate, returnDate)
                .stream().filter(flight -> flight.getCapacity() > flight.getBookings().size()).collect(Collectors.toList());
    }
}
