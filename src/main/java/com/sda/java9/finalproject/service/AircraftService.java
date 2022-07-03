package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AircraftDAO;
import com.sda.java9.finalproject.dto.AircraftDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class AircraftService {

    private final AircraftDAO aircraftDAO;

    public List<AircraftDTO> findAll() {
        return aircraftDAO.findAll();
    }

    public AircraftDTO findById(Long id) {
        return aircraftDAO.findById(id);
    }

    public void save(AircraftDTO aircraftDTO) {
        aircraftDAO.save(aircraftDTO);
    }

    public String deleteById(Long id) {
        aircraftDAO.deleteById(id);
        return String.format("Aircraft %d is deleted successfully.", id);
    }
}
