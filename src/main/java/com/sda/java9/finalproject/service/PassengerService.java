package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.PassengerDAO;
import com.sda.java9.finalproject.model.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class PassengerService {
    private final PassengerDAO passengerDAO;


    public List<Passenger> findAll() {
        return passengerDAO.findAll();
    }

    public Passenger findById(Long id) {
        return passengerDAO.findById(id);
    }

    public void save(Passenger passenger) {
        passengerDAO.save(passenger);
    }

    public String deleteById(Long id) {
        passengerDAO.deleteById(id);
        return  String.format("Passenger %d is deleted", id);
    }
}
