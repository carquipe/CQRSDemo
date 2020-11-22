package com.example.demo.events;

public class UserContactAddedEvent extends Event {
    private String contactType;
    private String contactDetails;

    public UserContactAddedEvent(String contactType, String contactDetails) {
        this.contactType = contactType;
        this.contactDetails = contactDetails;
    }
}
