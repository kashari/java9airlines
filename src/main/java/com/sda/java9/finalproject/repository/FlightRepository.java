package com.sda.java9.finalproject.repository;

import com.sda.java9.finalproject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT * FROM flight " +
            "WHERE departure_airport_id=:departureAirportId " +
            "AND arrival_airport_id=:arrivalAirportId " +
            "AND departure_date=:departureDate OR return_date=:returnDate ", nativeQuery = true)
    List<Flight> filteredFlights(@Param("departureAirportId") String departureAirportId,
                                      @Param("arrivalAirportId") String arrivalAirportId,
                                      @Param("departureDate") String departureDate,
                                      @Param("returnDate") String returnDate);

    @Query(value = "SELECT count(bp.passengers_id) " +
            " FROM flight f INNER JOIN booking b ON f.id = b.flight_id " +
            "INNER JOIN booking_passengers bp ON b.id = bp.booking_id " +
            "WHERE departure_airport_id=?1 " +
            "AND arrival_airport_id=?2 " +
            "AND departure_date=?3 OR return_date=?4 ", nativeQuery = true)
    Integer getPassengersCountPerFlight(@Param("departureAirportId") String departureAirportId,
                                        @Param("arrivalAirportId") String arrivalAirportId,
                                        @Param("departureDate") String departureDate,
                                        @Param("returnDate") String returnDate);
}
