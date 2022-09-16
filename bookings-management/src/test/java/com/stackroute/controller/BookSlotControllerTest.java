//package com.stackroute.controller;
//
//import com.stackroute.Booking.Controller.BookSlotController;
//import com.stackroute.Booking.Entity.BookSlot;
//import com.stackroute.Booking.Service.BookSlotServiceImpl;
//import com.stackroute.ID.SequenceGenerator;
//import com.stackroute.Slot.entity.Slot;
//import com.stackroute.Slot.entity.Status;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.stackroute.Slot.entity.Slot.SEQUENCE;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//public class BookSlotControllerTest {
//    @InjectMocks
//    BookSlotController bookSlotController;
//    @Mock
//    BookSlotServiceImpl bookSlotService;
//    @Mock
//    RabbitTemplate template;
//    @Mock
//    SequenceGenerator sequenceGenerator;
//    BookSlot bookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//
//    ResponseEntity responseEntity;
//    @Test
//    public void testWhenBookingisSuccess(){
//        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//        responseEntity = new ResponseEntity<>(bookSlot, HttpStatus.ACCEPTED);
//        bookSlot.setBookingID("BookingId-"+ Mockito.when(sequenceGenerator.sequenceNumber(SEQUENCE)).thenReturn(1));
//        Mockito.when(bookSlotService.bookSlot(checkBookSlot)).thenReturn(bookSlot);
//        assertEquals(responseEntity.toString(),bookSlotController.bookSlot(checkBookSlot).toString());
//    }
//    @Test
//    public void testWhenBookingisFailure(){
//        BookSlot checkBookSlot = null;
//        responseEntity = new ResponseEntity<>(checkBookSlot,HttpStatus.BAD_REQUEST);
//        Mockito.when(bookSlotService.bookSlot(checkBookSlot)).thenReturn(bookSlot);
//        assertEquals(responseEntity,bookSlotController.bookSlot(checkBookSlot));
//
//    }
//
//
//    @Test
//    public void testWhenUpdateisSuccess(){
//        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//        responseEntity = new ResponseEntity<>(bookSlot,HttpStatus.CREATED);
//        Mockito.when(bookSlotService.updateBooking(checkBookSlot)).thenReturn(bookSlot);
//        assertEquals(responseEntity,bookSlotController.updateBooking(checkBookSlot));
//
//    }
//    @Test
//    public void testWhenUpdateisFailure(){
//        responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
//        Mockito.when(bookSlotService.updateBooking(null)).thenReturn(null);
//        assertEquals(responseEntity,bookSlotController.updateBooking(null));
//    }
//
////    @Test
////    public void testWhenGetBookingsisSuccess(){
////        List<BookSlot> checkBookSlots=new ArrayList<>();
////        BookSlot checkBookSlot1 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
////        BookSlot checkBookSlot2 = new BookSlot("BookingId-2","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
////        checkBookSlots.add(checkBookSlot1);
////        checkBookSlots.add(checkBookSlot2);
////        responseEntity= new ResponseEntity<>(checkBookSlots,HttpStatus.FOUND);
////        Mockito.when(bookSlotService.getBookings()).thenReturn(checkBookSlots);
////        assertEquals(responseEntity,bookSlotController.getAllBookings());
////
////
////    }
////    @Test
////    public void testWhenGetBookingsisFailure(){
////        responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////        Mockito.when(bookSlotService.getBookings()).thenReturn(null);
////        assertEquals(responseEntity,bookSlotController.getAllBookings());
////    }
//    @Test
//    public void testWhenGetBookingisSuccess(){
//        BookSlot checkBookSlot = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//        responseEntity = new ResponseEntity<>(checkBookSlot,HttpStatus.FOUND);
//        Mockito.when(bookSlotService.getBooking("BookingID-1")).thenReturn(bookSlot);
//        assertEquals(responseEntity.toString(),bookSlotController.getBooking("BookingID-1").toString());
//    }
//    @Test
//    public void testWhenGetBookingisFailure(){
//        BookSlot checkBookSlot = null;
//        responseEntity= new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        Mockito.when(bookSlotService.getBooking(" ")).thenReturn(null);
//        assertEquals(responseEntity,bookSlotController.getBooking(" "));
//
//    }
//
//    @Test
//    public void testWhenGetBookingByDateisSuccess(){
//        List<BookSlot> checkBookSlots=new ArrayList<>();
//        BookSlot checkBookSlot1 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//        BookSlot checkBookSlot2 = new BookSlot("BookingId-1","playeremail@gmail.com","player1","owneremail@gmail.com","GroundName-1","city","addressLineOne","SlotID-1", LocalDate.now(), LocalTime.NOON,LocalTime.MIDNIGHT,Status.BOOKED);
//        checkBookSlots.add(checkBookSlot1);
//        checkBookSlots.add(checkBookSlot2);
//        responseEntity= new ResponseEntity<>(checkBookSlots,HttpStatus.FOUND);
//        Mockito.when(bookSlotService.getBookingsByDate(LocalDate.now())).thenReturn(checkBookSlots);
//        assertEquals(responseEntity,bookSlotController.getBookingByDate(LocalDate.now()));
//
//    }
//
//
//    @Test
//    public void testWhenGetBookingByDateisFailure(){
//        List<BookSlot> checkBookSlots=null;
//        responseEntity = new ResponseEntity<>(checkBookSlots,HttpStatus.NOT_FOUND);
//        Mockito.when(bookSlotService.getBookingsByDate(null)).thenReturn(null);
//        assertEquals(responseEntity,bookSlotController.getBookingByDate(null));
//    }
//    @Test
//    public void testWhenDeleteBookingisSuccess(){
//        responseEntity = new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
//        Mockito.when(bookSlotService.deleteBooking("BookingID-1")).thenReturn(true);
//        assertEquals(responseEntity,bookSlotController.deleteSlot("BookingID-1"));
//    }
//    @Test
//    public void testWhenDeleteBookingisFailure(){
//        responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        Mockito.when(bookSlotService.deleteBooking(" ")).thenReturn(false);
//        assertEquals(responseEntity,bookSlotController.deleteSlot(" "));
//    }
//
//
//
//}