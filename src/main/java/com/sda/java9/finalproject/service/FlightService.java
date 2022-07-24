package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AirportDAO;
import com.sda.java9.finalproject.dao.FlightDAO;
import com.sda.java9.finalproject.dto.FlightDTO;
import com.sda.java9.finalproject.entity.FlightClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FlightService {

    private final FlightDAO flightDAO;
    private final AirportDAO airportDAO;

    public List<FlightDTO> findAll() {
        return flightDAO.findAll();
    }

    public FlightDTO findById(Long id) {
        return flightDAO.findById(id);
    }

    public void save(FlightDTO flight) {
        if (!flight.isBiDirectional()) {
            flight.setReturnDate(null);
        }
        flightDAO.save(flight);
    }

    public String deleteById(Long id) {
        flightDAO.deleteById(id);
        return String.format("Flight with number %d deleted successfully.", id);
    }

    public List<FlightDTO> findFlights(String departureAirportId, String arrivalAirportId, String departureDate, String returnDate) {
        return flightDAO.findFlightsBySuperQuery(departureAirportId, arrivalAirportId, departureDate, returnDate)
                .stream().filter(f -> f.getCapacity() >
                        flightDAO.countOfPassengers(departureAirportId, arrivalAirportId, departureDate, returnDate))
                .collect(Collectors.toList());
    }

    public void saveAll(InputStream inputStream) throws IOException, ParseException {
        Set<FlightDTO> flightDTOS = readInputStream(inputStream);
        flightDTOS.stream().filter(f -> !f.isBiDirectional()).forEach(f -> f.setReturnDate(null));
        flightDAO.saveAll(flightDTOS);
    }

    private Set<FlightDTO> readInputStream(InputStream inputStream) throws IOException, ParseException {
        Set<FlightDTO> flights = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int count = 0;

        while((line = br.readLine()) != null){
            if (count == 0){
                count++;
                continue;
            }
            String[] columns = line.split(",");
            FlightDTO flightDTO = new FlightDTO();
            flightDTO.setDepartureAirport(airportDAO.findById(Long.valueOf(columns[0])));
            flightDTO.setArrivalAirport(airportDAO.findById(Long.valueOf(columns[1])));
            flightDTO.setDepartureDate(new SimpleDateFormat("yyyy-MM-dd").parse(columns[2]));
            flightDTO.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(columns[3]));
            flightDTO.setBiDirectional(Boolean.parseBoolean(columns[4]));
            flightDTO.setCapacity(Integer.parseInt(columns[5]));
            flightDTO.setFlightPrice(Integer.parseInt(columns[6]));
            flightDTO.setFlightClass(FlightClass.valueOf(columns[7]));
            flights.add(flightDTO);
            count++;
        }
        return flights;
    }
}
