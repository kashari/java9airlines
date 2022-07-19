package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dao.BookingDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingDAO bookingDAO;
    private final AppUserDAO appUserDAO;

    public List<BookingDTO> findAll() {
        return bookingDAO.findAll();
    }

    public BookingDTO findById(Long id) {
        return bookingDAO.findById(id);
    }

    public void save(BookingDTO booking, Authentication authentication) {
        booking.getPassengers().forEach(p -> {booking.getPassengers().add(p);});
        booking.setAppUserDTO(appUserDAO.findByUsername(authentication.getName()));
        bookingDAO.save(booking);
    }

    public List<BookingDTO> filterBookingsByUser(Authentication authentication){
        AppUserDTO userDTO = appUserDAO.findByUsername(authentication.getName());
        return bookingDAO.filterBookingsByAuthUser(userDTO);
    }
}
