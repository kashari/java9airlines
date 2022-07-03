package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.AircraftDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.generics.GenericMapper;
import com.sda.java9.finalproject.model.Aircraft;
import com.sda.java9.finalproject.repository.AircraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class AircraftDAO implements GenericDAO<AircraftDTO>, GenericMapper<Aircraft, AircraftDTO> {

    private final AircraftRepository aircraftRepository;

    @Override
    public List<AircraftDTO> findAll() {
        return aircraftRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AircraftDTO findById(Long id) {
        return aircraftRepository.findById(id).map(this::mapToDto).orElse(null);
    }

    @Override
    public void save(AircraftDTO aircraftDTO) {
        aircraftRepository.save(mapToEntity(aircraftDTO));
    }

    @Override
    public void deleteById(Long id) {
        aircraftRepository.deleteById(id);
    }

    @Override
    public Aircraft mapToEntity(AircraftDTO aircraftDTO) {
        Aircraft aircraft = new Aircraft();
        // map using getters and setters
        aircraft.setId(aircraftDTO.getId());
        aircraft.setMake(aircraftDTO.getMake());
        aircraft.setModel(aircraftDTO.getModel());
        aircraft.setCapacity(aircraftDTO.getCapacity());
        return aircraft;
    }

    @Override
    public AircraftDTO mapToDto(Aircraft aircraft) {
        AircraftDTO aircraftDTO = new AircraftDTO();
        // map using getters and setters
        aircraftDTO.setId(aircraft.getId());
        aircraftDTO.setMake(aircraft.getMake());
        aircraftDTO.setModel(aircraft.getModel());
        aircraftDTO.setCapacity(aircraft.getCapacity());
        return aircraftDTO;
    }
}
