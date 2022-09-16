package com.stackroute.Booking.Service;

import com.stackroute.Booking.Entity.BookSlot;
import com.stackroute.Booking.Repository.BookSlotRepository;
import com.stackroute.RabbitMQ.BookingDTO;
import com.stackroute.RabbitMQ.Producer;
import com.stackroute.Slot.entity.Status;
import com.stackroute.Slot.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookSlotServiceImpl implements BookSlotService {
    @Autowired
    private BookSlotRepository bookSlotRepository;
    @Autowired
    private Producer producer;
    @Autowired
    private SlotRepository slotRepository;
    @Override
    public BookSlot bookSlot(BookSlot bookSlot)  {


            bookSlotRepository.save(bookSlot);
            BookingDTO bookingDTO =new BookingDTO();
            bookingDTO.setBookingID(bookSlot.getBookingID());
            bookingDTO.setPlayerEmail(bookSlot.getPlayerEmail());
            bookingDTO.setOwnerEmail(bookSlot.getOwnerEmail());
            bookingDTO.setSlotID(bookSlot.getSlotID());
            bookingDTO.setPlayerName(bookSlot.getPlayerName());
            bookingDTO.setAddress(bookSlot.getAddressLineOne());
            bookingDTO.setCity(bookSlot.getCity());
            bookingDTO.setGroundName(bookSlot.getGroundName());
            bookingDTO.setStatus(bookSlot.getStatus());
            bookingDTO.setSlotEndTime(bookSlot.getSlotEndTime());
            bookingDTO.setSlotStartTime(bookSlot.getSlotStartTime());
            bookingDTO.setSlotDate(bookSlot.getSlotDate());
            System.out.println(bookingDTO.toString());
            boolean send = producer.sendMessageToRabbitMq(bookingDTO);
           if( !send){

               return null;
           }

        return bookSlot;
    }

    @Override
    public BookSlot updateBooking(BookSlot bookSlot) {


            BookSlot updatedSlot = bookSlotRepository.findById(bookSlot.getBookingID()).get();
                updatedSlot.setBookingID(bookSlot.getBookingID());
                updatedSlot.setPlayerEmail(bookSlot.getPlayerEmail());
                updatedSlot.setSlotDate(bookSlot.getSlotDate());
                updatedSlot.setStatus(bookSlot.getStatus());
                updatedSlot.setSlotID(bookSlot.getSlotID());
                updatedSlot.setSlotEndTime(bookSlot.getSlotEndTime());
                updatedSlot.setSlotStartTime(bookSlot.getSlotStartTime());
                updatedSlot.setOwnerEmail(bookSlot.getOwnerEmail());
                updatedSlot.setAddressLineOne(bookSlot.getAddressLineOne());
                updatedSlot.setCity(bookSlot.getCity());
                updatedSlot.setPlayerName(bookSlot.getPlayerName());
                updatedSlot.setGroundName(bookSlot.getGroundName());
                return bookSlotRepository.save(updatedSlot);






    }

    @Override
    public List<BookSlot> getBookings(String playerEmail) {
        List<BookSlot> allSlots = bookSlotRepository.findByPlayerEmail(playerEmail);

        return allSlots;
    }

    @Override
    public BookSlot getBooking(String bookingID) {
        if(bookingID==null){
            return null;
        }
        BookSlot bookSlot = bookSlotRepository.findById(bookingID).orElse(null);

        return bookSlot;
    }

    @Override
    public List<BookSlot> getBookingsByDate(LocalDate slotDate,String playerEmail) {
        List<BookSlot> bookings = bookSlotRepository.findBySlotDateAndPlayerEmail(slotDate,playerEmail);

        return bookings;
    }

    @Override
    public boolean deleteBooking(String bookingID) {
        if(bookSlotRepository.existsById(bookingID)){
            bookSlotRepository.deleteById(bookingID);
            return true;
        }
        return false;
    }

    @Override
    public List<BookSlot> getBookingsByStatus(String playerEmail, String status) {
        return bookSlotRepository.findByPlayerEmailAndStatus(playerEmail, status);
    }
    @Override
    public List<BookSlot> getBookingsByPlayerEmailAndStatusAndDate(String playerEmail, String status, LocalDate slotDate) {
        List<BookSlot> bookings =  bookSlotRepository.findByPlayerEmailAndStatusAndSlotDate(playerEmail,status,slotDate);
        if(bookings==null){
            return null;
        }
        return bookings;
    }

    @Override
    public BookSlot cancelBookingsByBookingIDAndStatus(String bookingID, String status) {
        BookSlot bookings = bookSlotRepository.findById(bookingID).orElse(null);
        bookings.setStatus("CANCELLED");
        String slotId = bookings.getSlotID();
        slotRepository.findById(slotId).ifPresent(x->x.setStatus(com.stackroute.Slot.entity.Status.AVAILABLE));
        bookSlotRepository.save(bookings);
        System.out.println(slotRepository.findById(slotId));

        return bookings;
    }

    @Override
    public List<BookSlot> getBookingsByOwnerEmailAndStatusAndDate(String ownerEmail, String status, LocalDate slotDate) {
        List<BookSlot> bookings =  bookSlotRepository.findByOwnerEmailAndStatusAndSlotDate(ownerEmail,status,slotDate);
        if(bookings==null){
            return null;
        }
        return bookings;
    }

    @Override
    public List<BookSlot> getBookingsByOwnerEmailAndStatus(String ownerEmail, String status) {

        List<BookSlot> bookings =  bookSlotRepository.findByOwnerEmailAndStatus(ownerEmail,status);
        if(bookings==null){
            return null;
        }
        return bookings;
    }
}


