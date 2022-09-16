package com.stackroute.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Messages {

   private String senderEmail;
   private String receiverEmail;
   private String messageContent;
   @JsonFormat(pattern = "HH:mm")
   private LocalTime messageTime;
   private LocalDate messageDate;



}
