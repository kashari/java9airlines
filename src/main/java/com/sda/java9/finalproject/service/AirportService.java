package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AirportDAO;
import com.sda.java9.finalproject.dto.AirportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class AirportService {

    private final AirportDAO airportDAO;

    public List<AirportDTO> findAll() {
        return airportDAO.findAll();
    }


    public AirportDTO findById(Long id) {
        return airportDAO.findById(id);
    }

    public void save(AirportDTO airportDTO) {
        airportDAO.save(airportDTO);
    }

    public String deleteById(Long id) {
        airportDAO.deleteById(id);
        return String.format("Airport with number %d deleted successfully.", id);
    }
}
