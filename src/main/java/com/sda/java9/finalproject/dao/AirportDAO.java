package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.AirportDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.generics.GenericMapper;
import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class AirportDAO implements GenericDAO<AirportDTO>, GenericMapper<Airport, AirportDTO> {

    private final AirportRepository airportRepository;

    @Override
    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AirportDTO findById(Long id) {
        return airportRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public void save(AirportDTO airportDTO) {
        airportRepository.save(mapToEntity(airportDTO));
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public Airport mapToEntity(AirportDTO airportDTO) {
        Airport airport = new Airport();
        airport.setId(airportDTO.getId());
        airport.setAirportCode(airportDTO.getAirportCode());
        airport.setName(airportDTO.getName());
        airport.setCity(airportDTO.getCity());
        airport.setFlagUrl(airportDTO.getFlagUrl());
        return airport;
    }

    @Override
    public AirportDTO mapToDto(Airport airport) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airport.getId());
        airportDTO.setAirportCode(airport.getAirportCode());
        airportDTO.setName(airport.getName());
        airportDTO.setCity(airport.getCity());
        airportDTO.setFlagUrl(airport.getFlagUrl());
        return airportDTO;
    }
}
