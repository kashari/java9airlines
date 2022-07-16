package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking WHERE flight_id=:flightId;", nativeQuery = true)
    List<Booking> findByFlightId(@Param("flightId") Long flightId);
}
