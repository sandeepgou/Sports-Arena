package com.stackroute.player.model;


public enum UserRole  {
    PLAYER("player"),
    GROUND_OWNER("groundOwner");
    private final String role;

    UserRole(String role){
        this.role=role;
    }
}
