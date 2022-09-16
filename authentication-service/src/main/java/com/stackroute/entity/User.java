package com.stackroute.entity;

import lombok.*;

import javax.persistence.Entity;


import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @Setter
    @Getter
    private String userEmail;
    @Getter
    @Setter
    private String password;
    @Setter
    @Getter
//    @Enumerated(EnumType.STRING)
    public String role;

}

