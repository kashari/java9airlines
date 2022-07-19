package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.dto.AirportDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class AirportDAO implements GenericDAO<AirportDTO> {

    private final AirportRepository airportRepository;

    @Override
    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream().map(AirlinesMapper::mapAirportToDTO).collect(Collectors.toList());
    }

    @Override
    public AirportDTO findById(Long id) {
        return airportRepository.findById(id).map(AirlinesMapper::mapAirportToDTO).orElse(null);
    }

    @Override
    public void save(AirportDTO airport) {
        airportRepository.save(AirlinesMapper.mapAirportDTOToEntity(airport));
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    public void saveAll(Set<AirportDTO> airportDTOS){
        airportRepository.saveAll(airportDTOS.stream().map(AirlinesMapper::mapAirportDTOToEntity).collect(Collectors.toSet()));
    }
}
