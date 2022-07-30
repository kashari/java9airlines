package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AirportDAO;
import com.sda.java9.finalproject.dto.AirportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class AirportService {

    private final AirportDAO airportDAO;

    public List<AirportDTO> findAll() {
        return airportDAO.findAll();
    }


    public AirportDTO findById(Long id) {
        return airportDAO.findById(id);
    }

    public void save(AirportDTO airport) {
        airportDAO.save(airport);
    }

    public String deleteById(Long id) {
        airportDAO.deleteById(id);
        return String.format("Airport with number %d deleted successfully.", id);
    }

    public void saveAll(InputStream inputStream) throws IOException {
        Set<AirportDTO> airportDTOS = readInputStreamHere(inputStream).stream().filter(a -> !airportDAO.existsByAirportCode(a.getAirportCode())).collect(Collectors.toSet());
        airportDAO.saveAll(airportDTOS);
    }

    private Set<AirportDTO> readInputStreamHere(InputStream inputStream) throws IOException {
        Set<AirportDTO> airports = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int count = 0;
        while ((line = br.readLine()) != null){
            if (count == 0){
                count ++;
                continue;
            }
            String[] columns = line.split(",");
            AirportDTO airport = new AirportDTO();
            airport.setAirportCode(columns[0]);
            airport.setName(columns[1]);
            airport.setCity(columns[2]);
            airport.setFlagUrl(columns[3]);
            airports.add(airport);
            count++;
        }
        return airports;
    }
}
