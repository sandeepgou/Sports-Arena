package com.stackroute.Slot.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Slot")
@Getter
@Setter
public class Slot {
    @Transient
    public static final String SEQUENCE = "slotID";
    @Id
    private String slotId;
    private String groundID;
    private LocalTime startTime;
    private LocalTime endTime;
    private Status status;
    private LocalDate slotDate;



}
