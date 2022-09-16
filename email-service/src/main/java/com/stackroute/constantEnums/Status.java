package com.stackroute.constantEnums;

public enum Status {
    FAILED("failed"),
    BOOKED("booked");
    private final String bookingStatus;
    Status(String bookingStatus ) {
        this.bookingStatus = bookingStatus;
    }
}

