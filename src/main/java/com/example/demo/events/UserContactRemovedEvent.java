package com.example.demo.events;

public class UserContactRemovedEvent extends Event {
    private String contactType;
    private String contactDetails;

    public UserContactRemovedEvent(String contactType, String contactDetails) {
        this.contactType = contactType;
        this.contactDetails = contactDetails;
    }
}
