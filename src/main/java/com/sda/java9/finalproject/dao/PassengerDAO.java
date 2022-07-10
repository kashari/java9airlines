package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.model.Passenger;
import com.sda.java9.finalproject.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class PassengerDAO implements GenericDAO<Passenger> {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }
}
