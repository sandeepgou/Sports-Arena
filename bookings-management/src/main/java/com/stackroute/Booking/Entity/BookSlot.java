package com.stackroute.Booking.Entity;

import com.stackroute.Slot.entity.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookSlot {
    @Transient
    public static final String SEQUENCE = "bookingID";
    @Id
    private String bookingID;
    private String playerEmail;
    private String playerName;
    private String ownerEmail;
    private String groundName;
    private String city;
    private String addressLineOne;
    private String slotID;
    private LocalDate slotDate;
    private LocalTime slotStartTime;
    private LocalTime slotEndTime;
    private String status;
}
