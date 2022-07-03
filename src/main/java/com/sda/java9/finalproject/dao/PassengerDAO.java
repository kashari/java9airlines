package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.PassengerDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.generics.GenericMapper;
import com.sda.java9.finalproject.model.Passenger;
import com.sda.java9.finalproject.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class PassengerDAO implements GenericDAO<PassengerDTO>, GenericMapper<Passenger, PassengerDTO> {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerDTO> findAll() {
        return passengerRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PassengerDTO findById(Long id) {
        return passengerRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public void save(PassengerDTO passengerDTO) {
        passengerRepository.save(mapToEntity(passengerDTO));
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public Passenger mapToEntity(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setId(passengerDTO.getId());
        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setAddress(passengerDTO.getAddress());
        passenger.setPassportCode(passengerDTO.getPassportCode());
        passenger.setFlight(passengerDTO.getFlight());
        passenger.setPassengerType(passengerDTO.getPassengerType());
        return passenger;
    }

    @Override
    public PassengerDTO mapToDto(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passenger.getId());
        passengerDTO.setFirstName(passenger.getFirstName());
        passengerDTO.setLastName(passenger.getLastName());
        passengerDTO.setAddress(passenger.getAddress());
        passengerDTO.setPassportCode(passenger.getPassportCode());
        passengerDTO.setFlight(passenger.getFlight());
        passengerDTO.setPassengerType(passenger.getPassengerType());
        return passengerDTO;
    }
}
