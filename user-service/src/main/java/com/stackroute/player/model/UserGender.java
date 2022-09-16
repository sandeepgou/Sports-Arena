package com.stackroute.player.model;

public enum UserGender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String gender;

    UserGender(String gender ){
        this.gender= gender;
    }


}
