package com.sda.java9.finalproject.generics;

import com.sda.java9.finalproject.dto.*;
import com.sda.java9.finalproject.entity.*;

import java.util.Objects;
import java.util.stream.Collectors;

public class AirlinesMapper {

    // Airport mappings

    public static AirportDTO mapAirportToDTO(Airport airport){
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airport.getId());
        airportDTO.setName(airport.getName());
        airportDTO.setAirportCode(airport.getAirportCode());
        airportDTO.setCity(airport.getCity());
        airportDTO.setFlagUrl(airport.getFlagUrl());
        return airportDTO;
    }

    public static Airport mapAirportDTOToEntity(AirportDTO airportDTO){
        Airport airport = new Airport();
        if (Objects.nonNull(airportDTO)) {
            airport.setId(airportDTO.getId());
            airport.setName(airportDTO.getName());
            airport.setAirportCode(airportDTO.getAirportCode());
            airport.setCity(airportDTO.getCity());
            airport.setFlagUrl(airportDTO.getFlagUrl());
        }
        return airport;
    }

    // Passenger mappings

    public static PassengerDTO mapPassengerToDTO(Passenger passenger){
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passenger.getId());
        passengerDTO.setFirstName(passenger.getFirstName());
        passengerDTO.setLastName(passenger.getLastName());
        passengerDTO.setAddress(passenger.getAddress());
        passengerDTO.setPassportCode(passenger.getPassportCode());
        passengerDTO.setPassengerType(passenger.getPassengerType());
        return passengerDTO;
    }

    public static Passenger mapPassengerDTOToEntity(PassengerDTO passengerDTO){
        Passenger passenger = new Passenger();
        if (Objects.nonNull(passengerDTO)) {
            passenger.setId(passengerDTO.getId());
            passenger.setFirstName(passengerDTO.getFirstName());
            passenger.setLastName(passengerDTO.getLastName());
            passenger.setAddress(passengerDTO.getAddress());
            passenger.setPassportCode(passengerDTO.getPassportCode());
            passenger.setPassengerType(passengerDTO.getPassengerType());
        }
        return passenger;
    }

    // Flight mappings

    public static FlightDTO mapFlightToDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setCapacity(flight.getCapacity());
        flightDTO.setDepartureAirport(AirlinesMapper.mapAirportToDTO(flight.getDepartureAirport()));
        flightDTO.setArrivalAirport(AirlinesMapper.mapAirportToDTO(flight.getArrivalAirport()));
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setReturnDate(flight.getReturnDate());
        flightDTO.setBiDirectional(flight.isBiDirectional());
        flightDTO.setFlightPrice(flight.getFlightPrice());
        flightDTO.setFlightClass(flight.getFlightClass());
        return flightDTO;
    }

    public static Flight mapFlightDTOToEntity(FlightDTO flightDTO){
        Flight flight = new Flight();
        if (Objects.nonNull(flightDTO)){
            flight.setId(flightDTO.getId());
            flight.setCapacity(flightDTO.getCapacity());
            flight.setDepartureAirport(AirlinesMapper.mapAirportDTOToEntity(flightDTO.getDepartureAirport()));
            flight.setArrivalAirport(AirlinesMapper.mapAirportDTOToEntity(flightDTO.getArrivalAirport()));
            flight.setDepartureDate(flightDTO.getDepartureDate());
            flight.setReturnDate(flightDTO.getReturnDate());
            flight.setBiDirectional(flightDTO.isBiDirectional());
            flight.setFlightPrice(flightDTO.getFlightPrice());
            flight.setFlightClass(flightDTO.getFlightClass());
        }
        return flight;
    }

    // Booking mappings

    public static BookingDTO mapBookingToDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setUuid(booking.getUuid());
        bookingDTO.setFlight(AirlinesMapper.mapFlightToDTO(booking.getFlight()));
        bookingDTO.setPassengers(booking.getPassengers().stream().map(AirlinesMapper::mapPassengerToDTO).collect(Collectors.toSet()));
        bookingDTO.setBaggage(booking.getBaggage());
        bookingDTO.setCheckedIn(booking.isCheckedIn());
        bookingDTO.setUser(AirlinesMapper.mapAppUserToDTO(booking.getUser()));
        return bookingDTO;
    }

    public static Booking mapBookingDTOToEntity(BookingDTO bookingDTO){
        Booking booking = new Booking();
        if (Objects.nonNull(bookingDTO)){
            booking.setId(bookingDTO.getId());
            booking.setUuid(bookingDTO.getUuid());
            booking.setFlight(AirlinesMapper.mapFlightDTOToEntity(bookingDTO.getFlight()));
            booking.setPassengers(bookingDTO.getPassengers().stream().map(AirlinesMapper::mapPassengerDTOToEntity).collect(Collectors.toSet()));
            booking.setBaggage(bookingDTO.getBaggage());
            booking.setCheckedIn(bookingDTO.isCheckedIn());
            booking.setUser(AirlinesMapper.mapAppUserDTOToEntity(bookingDTO.getUser()));
        }
        return booking;
    }


    // AppRole mappings

    public static AppRoleDTO mapAppRoleToDTO(AppRole appRole){
        AppRoleDTO appRoleDTO = new AppRoleDTO();
        appRoleDTO.setId(appRole.getId());
        appRoleDTO.setName(appRole.getName());
        return appRoleDTO;
    }

    public static AppRole mapAppRoleDTOToEntity(AppRoleDTO appRoleDTO){
        AppRole appRole = new AppRole();
        if (Objects.nonNull(appRoleDTO)){
            appRole.setId(appRoleDTO.getId());
            appRole.setName(appRoleDTO.getName());
        }
        return appRole;
    }

    // AppUser mappings

    public static AppUserDTO mapAppUserToDTO(AppUser appUser){
        AppUserDTO appUserDTO = new AppUserDTO();
        if (Objects.nonNull(appUser)) {
            appUserDTO.setId(appUser.getId());
            appUserDTO.setUsername(appUser.getUsername());
            appUserDTO.setPassword(appUser.getPassword());
            appUserDTO.setEmail(appUser.getEmail());
            appUserDTO.setProfession(appUser.getProfession());
            appUserDTO.setRoles(appUser.getRoles().stream().map(AirlinesMapper::mapAppRoleToDTO).collect(Collectors.toSet()));
            appUserDTO.setIsEnabled(appUser.getIsEnabled());
        }
        return appUserDTO;
    }

    public static AppUser mapAppUserDTOToEntity(AppUserDTO appUserDTO){
        AppUser appUser = new AppUser();
        if (Objects.nonNull(appUserDTO)){
            appUser.setId(appUserDTO.getId());
            appUser.setUsername(appUserDTO.getUsername());
            appUser.setPassword(appUserDTO.getPassword());
            appUser.setEmail(appUserDTO.getEmail());
            appUser.setProfession(appUserDTO.getProfession());
            appUser.setRoles(appUserDTO.getRoles().stream().map(AirlinesMapper::mapAppRoleDTOToEntity).collect(Collectors.toSet()));
            appUser.setIsEnabled(appUserDTO.getIsEnabled());
        }
        return appUser;
    }

    // Post mappings

    public static PostDTO mapPostToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setAuthor(AirlinesMapper.mapAppUserToDTO(post.getAuthor()));
        postDTO.setReviews(post.getReviews().stream().map(AirlinesMapper::mapReviewToDTO).collect(Collectors.toList()));
        postDTO.setCreatedAt(post.getCreatedAt());
        return postDTO;
    }

    public static Post mapPostDTOToEntity(PostDTO postDTO){
        Post post = new Post();
        if (Objects.nonNull(postDTO)) {
            post.setId(postDTO.getId());
            post.setTitle(postDTO.getTitle());
            post.setDescription(postDTO.getDescription());
            post.setAuthor(AirlinesMapper.mapAppUserDTOToEntity(postDTO.getAuthor()));
            if (Objects.nonNull(postDTO.getReviews())){
                post.setReviews(postDTO.getReviews().stream().map(AirlinesMapper::mapReviewDTOToEntity).collect(Collectors.toList()));
            }
            post.setCreatedAt(postDTO.getCreatedAt());
        }
        return post;
    }

    // Review mappings

    public static ReviewDTO mapReviewToDTO(Review review){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setBody(review.getBody());
        reviewDTO.setUser(AirlinesMapper.mapAppUserToDTO(review.getUser()));
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }

    public static Review mapReviewDTOToEntity(ReviewDTO reviewDTO){
        Review review = new Review();
        if (Objects.nonNull(reviewDTO)) {
            review.setId(reviewDTO.getId());
            review.setBody(reviewDTO.getBody());
            review.setUser(AirlinesMapper.mapAppUserDTOToEntity(reviewDTO.getUser()));
            review.setCreatedAt(reviewDTO.getCreatedAt());
        }
        return review;
    }
}
