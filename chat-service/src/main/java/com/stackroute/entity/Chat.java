package com.stackroute.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat {
      @Transient
      public static final String SEQUENCE = "chatId";
      @Id
      private String  chatId;
      private String playerAEmailId;
      private String playerBEmailId;
      private List<Messages> messages;



}
