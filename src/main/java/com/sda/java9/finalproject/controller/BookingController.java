package com.sda.java9.finalproject.controller;

import com.sda.java9.finalproject.dto.BookingDTO;
import com.sda.java9.finalproject.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/bookings") @CrossOrigin("http://localhost:4200")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public List<BookingDTO> findAll(){
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public BookingDTO findById(@PathVariable("id") Long id){
        return bookingService.findById(id);
    }

    @PostMapping
    public BookingDTO save(@RequestBody BookingDTO booking){
        bookingService.save(booking);
        return booking;
    }
}