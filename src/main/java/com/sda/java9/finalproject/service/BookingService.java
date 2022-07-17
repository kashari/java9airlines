package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.BookingDAO;
import com.sda.java9.finalproject.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingDAO bookingDAO;

    public List<BookingDTO> findAll() {
        return bookingDAO.findAll();
    }

    public BookingDTO findById(Long id) {
        return bookingDAO.findById(id);
    }

    public void save(BookingDTO booking) {
        booking.getPassengers().forEach(p -> {booking.getPassengers().add(p);});
        bookingDAO.save(booking);
    }
}
