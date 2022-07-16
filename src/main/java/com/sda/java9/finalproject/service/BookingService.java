package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.BookingDAO;
import com.sda.java9.finalproject.dao.FlightDAO;
import com.sda.java9.finalproject.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingDAO bookingDAO;
    private final FlightDAO flightDAO;

    public List<Booking> findAll() {
        return bookingDAO.findAll();
    }

    public Booking findById(Long id) {
        return bookingDAO.findById(id);
    }

    public void save(Booking booking) {
        booking.getPassengers().forEach(b -> {booking.getPassengers().add(b);});
        bookingDAO.save(booking);
    }
}
