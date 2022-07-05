package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.generics.GenericMapper;
import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class FlightDAO implements GenericDAO<FlightDTO>, GenericMapper<Flight, FlightDTO> {

    private final FlightRepository flightRepository;

    @Override
    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public FlightDTO findById(Long id) {
        return flightRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public void save(FlightDTO flightDTO) {
        flightRepository.save(mapToEntity(flightDTO));
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    // TODO: method that returns the result set from the native query.
    public List<FlightDTO> getResultFromNativeQuery(String departureAirportId, String arrivalAirportId, String departureDate){
        return flightRepository.fullSearchFlights(departureAirportId, arrivalAirportId, departureDate)
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Flight mapToEntity(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setCapacity(flightDTO.getCapacity());
        flight.setDepartureAirport(flightDTO.getDepartureAirport());
        flight.setArrivalAirport(flightDTO.getArrivalAirport());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setFlightClass(flightDTO.getFlightClass());
        flight.setPassengers(flightDTO.getPassengers());
        flight.setFlightPrice(flightDTO.getFlightPrice());
        return flight;
    }

    @Override
    public FlightDTO mapToDto(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setCapacity(flight.getCapacity());
        flightDTO.setDepartureAirport(flight.getDepartureAirport());
        flightDTO.setArrivalAirport(flight.getArrivalAirport());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setFlightClass(flight.getFlightClass());
        flightDTO.setPassengers(flight.getPassengers());
        flightDTO.setFlightPrice(flight.getFlightPrice());
        return flightDTO;
    }
}
