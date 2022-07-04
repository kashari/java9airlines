package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    // running a native SQL query to perform a complex search and implement passenger capacity logic in the service
    @Transactional @Query(value = "SELECT * FROM flight WHERE " +
            "`departure_airport_id`=:departureAirportId" +
            " AND `arrival_airport_id`=:arrivalAirportId" +
            " AND `departure_date`=:departureDate AND `arrival_date`=:arrivalDate;", nativeQuery = true)
    List<Flight> fullSearchFlights(Long departureAirportId, Long arrivalAirportId, Date departureDate, Date arrivalDate);
}
