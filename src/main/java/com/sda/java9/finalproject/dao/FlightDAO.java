package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class FlightDAO implements GenericDAO<FlightDTO> {

    private final FlightRepository flightRepository;

    @Override
    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream().map(AirlinesMapper::mapFlightToDTO).collect(Collectors.toList());
    }

    @Override
    public FlightDTO findById(Long id) {
        return flightRepository.findById(id).map(AirlinesMapper::mapFlightToDTO).orElse(null);
    }

    @Override
    public void save(FlightDTO flight) {
        flightRepository.save(AirlinesMapper.mapFlightDTOToEntity(flight));
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    public List<FlightDTO> findFlightsBySuperQuery(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate) {
        return flightRepository.filteredFlights(departureAirportId, arrivalAirportId, departureDate, returnDate)
                .stream().map(AirlinesMapper::mapFlightToDTO).collect(Collectors.toList());
    }

    public Integer countOfPassengers(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate){
        return flightRepository.getPassengersCountPerFlight(departureAirportId, arrivalAirportId, departureDate, returnDate);
    }

    public void saveAll(Set<FlightDTO> flightDTOS){
        flightRepository.saveAll(flightDTOS.stream().map(AirlinesMapper::mapFlightDTOToEntity).collect(Collectors.toSet()));
    }
}
