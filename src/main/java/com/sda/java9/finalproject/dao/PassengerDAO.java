package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.PassengerDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class PassengerDAO implements GenericDAO<PassengerDTO> {

    private final PassengerRepository passengerRepository;

    @Override
    public List<PassengerDTO> findAll() {
        return passengerRepository.findAll().stream().map(AirlinesMapper::mapPassengerToDTO).collect(Collectors.toList());
    }

    @Override
    public PassengerDTO findById(Long id) {
        return passengerRepository.findById(id).map(AirlinesMapper::mapPassengerToDTO).orElse(null);
    }

    @Override
    public void save(PassengerDTO passenger) {
        passengerRepository.save(AirlinesMapper.mapPassengerDTOToEntity(passenger));
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }
}
