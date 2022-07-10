package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.model.Booking;
import com.sda.java9.finalproject.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor
public class BookingDAO implements GenericDAO<Booking> {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
