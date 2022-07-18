package com.sda.java9.finalproject.generics;

import com.sda.java9.finalproject.dto.*;
import com.sda.java9.finalproject.model.Airport;
import com.sda.java9.finalproject.model.Booking;
import com.sda.java9.finalproject.model.Flight;
import com.sda.java9.finalproject.model.Passenger;
import com.sda.java9.finalproject.security.entity.AppRole;
import com.sda.java9.finalproject.security.entity.AppUser;

import java.util.Objects;
import java.util.stream.Collectors;

public class AirlinesMapper {

    // airport mappings

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

    // passenger mappings

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

    // flight mappings

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

    // booking mappings

    public static BookingDTO mapBookingToDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setFlight(AirlinesMapper.mapFlightToDTO(booking.getFlight()));
        bookingDTO.setPassengers(booking.getPassengers().stream().map(AirlinesMapper::mapPassengerToDTO).collect(Collectors.toSet()));
        bookingDTO.setBaggage(booking.getBaggage());
        bookingDTO.setCheckedIn(booking.isCheckedIn());
        return bookingDTO;
    }

    public static Booking mapBookingDTOToEntity(BookingDTO bookingDTO){
        Booking booking = new Booking();
        if (Objects.nonNull(bookingDTO)){
            booking.setId(bookingDTO.getId());
            booking.setFlight(AirlinesMapper.mapFlightDTOToEntity(bookingDTO.getFlight()));
            booking.setPassengers(bookingDTO.getPassengers().stream().map(AirlinesMapper::mapPassengerDTOToEntity).collect(Collectors.toSet()));
            booking.setBaggage(bookingDTO.getBaggage());
            booking.setCheckedIn(bookingDTO.isCheckedIn());
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
        appUserDTO.setId(appUser.getId());
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setPassword(appUser.getPassword());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setRoles(appUser.getRoles());
        return appUserDTO;
    }

    public static AppUser mapAppUserDTOToEntity(AppUserDTO appUserDTO){
        AppUser appUser = new AppUser();
        if (Objects.nonNull(appUserDTO)){
            appUser.setId(appUserDTO.getId());
            appUser.setUsername(appUserDTO.getUsername());
            appUser.setPassword(appUserDTO.getPassword());
            appUser.setEmail(appUserDTO.getEmail());
            appUser.setRoles(appUserDTO.getRoles());
        }
        return appUser;
    }
}
