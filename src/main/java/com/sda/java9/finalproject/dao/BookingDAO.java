package com.sda.java9.finalproject.dao;

import com.sda.java9.finalproject.dto.BookingDTO;
import com.sda.java9.finalproject.generics.AirlinesMapper;
import com.sda.java9.finalproject.generics.GenericDAO;
import com.sda.java9.finalproject.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component @RequiredArgsConstructor
public class BookingDAO implements GenericDAO<BookingDTO> {

    private final BookingRepository bookingRepository;

    @Override
    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream().map(AirlinesMapper::mapBookingToDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO findById(Long id) {
        return bookingRepository.findById(id).map(AirlinesMapper::mapBookingToDTO).orElse(null);
    }

    @Override
    public void save(BookingDTO booking) {
        bookingRepository.save(AirlinesMapper.mapBookingDTOToEntity(booking));
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<BookingDTO> findByFlightId(Long id){
        return bookingRepository.findByFlightId(id).stream().map(AirlinesMapper::mapBookingToDTO).collect(Collectors.toList());
    }
}
