package com.stackroute.player.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection ="player")
@ToString
public class Player {
    @Id
    private String userEmail;
    private String name;
    private String phoneNumber;
    private String password;
    private String city;
    private String state;
    private String profilePhoto;
    private String favSport;
    private String role;
    private String gender;
}
