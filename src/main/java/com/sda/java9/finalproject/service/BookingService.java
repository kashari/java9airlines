package com.sda.java9.finalproject.service;

import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dao.BookingDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.dto.BookingDTO;
import com.sda.java9.finalproject.dto.PassengerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingDAO bookingDAO;
    private final AppUserDAO appUserDAO;

    private final JavaMailSender mailSender;

    public List<BookingDTO> findAll() {
        return bookingDAO.findAll();
    }

    public BookingDTO findById(Long id) {
        return bookingDAO.findById(id);
    }

    public void save(BookingDTO booking, Authentication authentication){
        try {
            booking.setUser(appUserDAO.findByUsername(authentication.getName()));
            booking.setUuid(UUID.randomUUID());
            sendMail("java9airlines@outlook.com", booking);
            bookingDAO.save(booking);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public List<BookingDTO> filterBookingsByUser(Authentication authentication){
        AppUserDTO userDTO = appUserDAO.findByUsername(authentication.getName());
        return bookingDAO.filterBookingsByAuthUser(userDTO);
    }

    public void sendMail(String from, BookingDTO booking) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(booking.getUser().getEmail());
        message.setSubject("Booking: "+booking.getUuid());
        message.setText(String.format("""
                Dear Customer,
                
                We are delighted once again doing business with you.
                We thank you for the booking made and note that in this email you will find all the details and can be used as the online ticket.
                
                You are traveling from %s to %s day: %s.
                
                %s
                
                With a total to pay from: %.2f EUR
                
                We wish you a nice trip.
                """, booking.getFlight().getDepartureAirport().getCity(), booking.getFlight().getArrivalAirport().getCity(),
                booking.getFlight().getDepartureDate(), allPassengers(booking.getPassengers()), booking.getFlight().getFlightPrice()));
        mailSender.send(message);
    }

    private List<String> allPassengers(Set<PassengerDTO> passengers){
        return passengers.stream().map(p -> String.format("%s %s %s", p.getLastName(), p.getFirstName(), p.getPassportCode())).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        bookingDAO.deleteById(id);
    }
}
