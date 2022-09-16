package com.stackroute.Booking.Repository;

import com.stackroute.Booking.Entity.BookSlot;
import com.stackroute.Slot.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookSlotRepository extends MongoRepository<BookSlot,String> {
    List<BookSlot> findBySlotDateAndPlayerEmail(LocalDate slotDate,String playerEmail);
    List<BookSlot> findByPlayerEmail(String playerEmail);

    List<BookSlot> findByPlayerEmailAndStatus(String playerEmail,String status);

    List<BookSlot> findByPlayerEmailAndStatusAndSlotDate(String playerEmail, String status, LocalDate slotDate);

    List<BookSlot> findByOwnerEmailAndStatus(String ownerEmail,String status);
    List<BookSlot> findByOwnerEmailAndStatusAndSlotDate(String ownerEmail,String status,LocalDate slotDate);
}
