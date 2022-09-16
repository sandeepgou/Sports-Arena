/*
package com.stackroute.service;

import com.stackroute.Booking.Entity.BookSlot;
import com.stackroute.Booking.Repository.BookSlotRepository;
import com.stackroute.Booking.Service.BookSlotServiceImpl;
import com.stackroute.RabbitMQ.BookingDTO;
import com.stackroute.RabbitMQ.Producer;
import com.stackroute.Slot.entity.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookSlotServiceTest {

    @InjectMocks
    BookSlotServiceImpl bookSlotService;

    @Mock
    BookSlotRepository bookSlotRepository;

    @Mock
    Producer producer;
    BookSlot bookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
    BookingDTO bookingDTO = new BookingDTO("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);

    @Test
    public void testWhenBookingisFailure(){
        Mockito.when(bookSlotRepository.save(bookSlot)).thenReturn(null);
        Mockito.when(producer.sendMessageToRabbitMq(bookingDTO)).thenReturn(false);
        assertEquals(null,bookSlotService.bookSlot(bookSlot));
    }
    @Test
    public void testWhenBookingisUpdated(){
        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        BookSlot checkBookSlot1 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        Mockito.when(bookSlotRepository.existsById(bookSlot.getBookingID())).thenReturn(true);
        Mockito.when(bookSlotRepository.save(checkBookSlot1)).thenReturn(checkBookSlot);
        assertEquals(checkBookSlot.toString(),bookSlotService.updateBooking(checkBookSlot1).toString());
    }
    @Test
    public void testWhenBookingisNotUpdated(){
        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        Mockito.when(bookSlotRepository.existsById("BookingId-1")).thenReturn(false);
        assertEquals(null,bookSlotService.updateBooking(checkBookSlot));
    }
    @Test
    public void testWhenGetBookingsisSuccess(){
        List<BookSlot> checkBookSlots=new ArrayList<>();
        BookSlot checkBookSlot1 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        BookSlot checkBookSlot2 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        checkBookSlots.add(checkBookSlot1);
        checkBookSlots.add(checkBookSlot2);
        Mockito.when(bookSlotRepository.findAll()).thenReturn(checkBookSlots);
        assertEquals(checkBookSlots,bookSlotService.getBookings());
    }
    @Test
    public void testWhenGetBookingsisFailure(){
        Mockito.when(bookSlotRepository.findAll()).thenReturn(null);
        assertEquals(null,bookSlotService.getBookings());
    }

    @Test
    public void testWhenGetBookingisSuccess(){
        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        Mockito.when(bookSlotRepository.findById("BookingId-1")).thenReturn(Optional.of(checkBookSlot));
        assertEquals(checkBookSlot,bookSlotService.getBooking("BookingId-1"));
    }
    @Test
    public void testWhenGetBookingisFailure(){
        Mockito.when(bookSlotRepository.findById(null)).thenReturn(null);
        assertEquals(null,bookSlotService.getBooking(null));
    }
    @Test
    public void testWhenGetBookingByDateisSuccess(){
        List<BookSlot> checkBookSlots=new ArrayList<>();
        BookSlot checkBookSlot1 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        BookSlot checkBookSlot2 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
        checkBookSlots.add(checkBookSlot1);
        checkBookSlots.add(checkBookSlot2);
        Mockito.when(bookSlotRepository.findBySlotDate(LocalDate.now())).thenReturn((List<BookSlot>) checkBookSlots);
        assertEquals(checkBookSlots,bookSlotService.getBookingsByDate(LocalDate.now()));


    }
    @Test
    public void testWhenGetBookingByDateisFailure(){
        Mockito.when(bookSlotRepository.findBySlotDate(null)).thenReturn(null);
        assertEquals(null,bookSlotService.getBookingsByDate(null));
    }
    @Test
    public void testWhenDeleteBookingisSuccess(){
        Mockito.when(bookSlotRepository.existsById("BookingId-1")).thenReturn(true);
        assertEquals(true,bookSlotService.deleteBooking("BookingId-1"));
    }
    @Test
    public void testWhenDeleteBookingisFailure(){
        Mockito.when(bookSlotRepository.existsById(null)).thenReturn(false);
        assertEquals(false, bookSlotService.deleteBooking(null));
    }

}*/
