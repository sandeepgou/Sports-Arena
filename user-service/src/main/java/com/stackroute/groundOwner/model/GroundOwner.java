package com.stackroute.groundOwner.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection ="ground")
public class GroundOwner {

//    @Transient
//    public static final String SEQUENCE = "groundID";
    @Id
    private String groundID = UUID.randomUUID().toString();
    private String ownerEmail;
    private String ownerPassword;
    private String groundName;
    private String groundType;
    private String addressLineOne;
    private String city;
    private String[] amenities;
    private String groundPicture;
    private String phoneNumber;
    private double charge;
}
