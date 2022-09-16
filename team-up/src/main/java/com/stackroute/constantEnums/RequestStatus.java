package com.stackroute.constantEnums;

public enum RequestStatus {

    PENDING("pending"),
    REJECTED("rejected"),
    ACCEPTED("accepted");

    private final String requestStatus;
    
    RequestStatus(String requestStatus ) {
        this.requestStatus= requestStatus;
    }
}
