package com.sda.java9.finalproject.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.sda.java9.finalproject.dao.AppUserDAO;
import com.sda.java9.finalproject.dao.BookingDAO;
import com.sda.java9.finalproject.dto.AppUserDTO;
import com.sda.java9.finalproject.dto.BookingDTO;
import com.sda.java9.finalproject.dto.PassengerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BookingService {
    private final BookingDAO bookingDAO;
    private final AppUserDAO appUserDAO;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String mailServer;

    public List<BookingDTO> findAll() {
        return bookingDAO.findAll();
    }

    public BookingDTO findById(Long id) {
        return bookingDAO.findById(id);
    }

    public void save(BookingDTO booking, Authentication authentication){
        booking.setUser(appUserDAO.findByUsername(authentication.getName()));
        booking.setUuid(UUID.randomUUID());
        sendMail("java9airlines@outlook.com", booking);
        bookingDAO.save(booking);
    }

    public List<BookingDTO> filterBookingsByUser(Authentication authentication){
        AppUserDTO userDTO = appUserDAO.findByUsername(authentication.getName());
        return bookingDAO.filterBookingsByAuthUser(userDTO);
    }

    public void sendMail(String from, BookingDTO booking) {
        Properties props = System.getProperties();
        props.put("spring.mail.host", mailServer);
        Session session = Session.getInstance(props);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(booking.getUser().getEmail()));
            message.setSubject("Booking: " + booking.getUuid());
            message.setSentDate(new Date());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            writeBookingToPDF(booking, outputStream);
            byte[] bytes = outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName(booking.getUuid().toString()+".pdf");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(pdfBodyPart);
            message.setContent(mimeMultipart);
            mailSender.send(message);

        } catch (MessagingException me){
            throw new RuntimeException(me);
        }
    }

    private List<String> allPassengers(Set<PassengerDTO> passengers){
        return passengers.stream().map(p -> String.format("%s %s %s", p.getLastName(), p.getFirstName(), p.getPassportCode())).collect(Collectors.toList());
    }

    private void writeBookingToPDF(BookingDTO booking, OutputStream stream) {
        Document doc = new Document();
        try{
            PdfWriter.getInstance(doc, stream);

            Font font = new Font(Font.FontFamily.HELVETICA, 16.5F, Font.NORMAL, BaseColor.BLACK);
            doc.open();
            Header header = new Header("Booking Header", "Booking: "+booking.getUuid().toString());
            String content = String.format("""
                
                Dear MR/MRS %s,
                              
                You are traveling from %s to %s day: %s.
                
                Passengers confirmed in this flight are:\040
                
                Baggage's: %s
                
                %s
                
                With a total to pay: %.2f EUR
                
                We wish you a nice trip.
                """, booking.getUser().getUsername(),  booking.getFlight().getDepartureAirport().getCity(), booking.getFlight().getArrivalAirport().getCity(),
                    booking.getFlight().getDepartureDate(), booking.getBaggage().name(), allPassengers(booking.getPassengers()), booking.getFlight().getFlightPrice());

            Paragraph para = new Paragraph(content, font);
            doc.add(header);
            doc.add(para);
            doc.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long id) {
        bookingDAO.deleteById(id);
    }
}
