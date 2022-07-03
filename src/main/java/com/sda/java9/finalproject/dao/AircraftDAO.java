package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.AircraftDTO;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.generics.GenericMapper;
import com.sda.java9.finalproject.model.Aircraft;
import com.sda.java9.finalproject.repository.AircraftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class AircraftDAO implements GenericDAO<AircraftDTO>, GenericMapper<Aircraft, AircraftDTO> {

    private final AircraftRepository aircraftRepository;

    @Override
    public List<AircraftDTO> findAll() {
        return null;
    }

    @Override
    public AircraftDTO findById(Long id) {
        return null;
    }

    @Override
    public void save(AircraftDTO aircraftDTO) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Aircraft mapToEntity(AircraftDTO aircraftDTO) {
        Aircraft aircraft = new Aircraft();
        // map using getters and setters
        return aircraft;
    }

    @Override
    public AircraftDTO mapToDto(Aircraft aircraft) {
        AircraftDTO aircraftDTO = new AircraftDTO();
        // map using getters and setters
        return aircraftDTO;
    }
}
