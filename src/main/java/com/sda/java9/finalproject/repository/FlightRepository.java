package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight WHERE " +
            "departure_airport_id=:departureAirportId" +
            " AND arrival_airport_id=:arrivalAirportId" +
            " OR departure_date=:departureDate", nativeQuery = true)
    List<Flight> fullSearchFlights(@Param("departureAirportId") String departureAirportId,
                                   @Param("arrivalAirportId") String arrivalAirportId,
                                   @Param("departureDate") String departureDate);
}
