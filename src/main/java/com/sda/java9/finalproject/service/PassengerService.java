package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.PassengerDAO;
import com.sda.java9.finalproject.dto.PassengerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class PassengerService {
    private final PassengerDAO passengerDAO;


    public List<PassengerDTO> findAll() {
        return passengerDAO.findAll();
    }

    public PassengerDTO findById(Long id) {
        return passengerDAO.findById(id);
    }

    public void save(PassengerDTO passengerDTO) {
        passengerDAO.save(passengerDTO);
    }

    public String deleteById(Long id) {
        passengerDAO.deleteById(id);
        return  String.format("Passenger %d is deleted", id);
    }
}
