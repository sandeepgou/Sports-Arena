package com.stackroute.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stackroute.constantEnums.RequestStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "InviteTable")
public class Teamup {
    @Id
    private String requestId;
    private String senderEmail;
    private String requestedPlayerEmail;
    private RequestStatus statusOfRequest;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate requestDate;
}
