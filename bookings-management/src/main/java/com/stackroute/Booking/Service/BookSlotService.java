package com.stackroute.Booking.Service;

import com.stackroute.Booking.Entity.BookSlot;

import java.time.LocalDate;
import java.util.List;


public interface BookSlotService {
    BookSlot bookSlot(BookSlot bookSlot);

    BookSlot updateBooking( BookSlot bookSlot);

    List<BookSlot> getBookings(String playerEmail);

   BookSlot getBooking(String bookingID);

    List<BookSlot> getBookingsByDate(LocalDate slotDate,String playerEmail);

    boolean deleteBooking(String slotID);

    List<BookSlot> getBookingsByStatus(String playerEmail,String status);

    List<BookSlot> getBookingsByPlayerEmailAndStatusAndDate(String playerEmail, String status, LocalDate slotDate);

    BookSlot cancelBookingsByBookingIDAndStatus(String bookingID, String status);

    List<BookSlot> getBookingsByOwnerEmailAndStatus(String ownerEmail, String status);
    List<BookSlot> getBookingsByOwnerEmailAndStatusAndDate(String ownerEmail, String status, LocalDate slotDate);
}
