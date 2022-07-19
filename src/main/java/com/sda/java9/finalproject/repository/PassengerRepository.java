package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByPassportCode(String passportCode);
    Boolean existsPassengerByPassportCode(String passportCode);
}
