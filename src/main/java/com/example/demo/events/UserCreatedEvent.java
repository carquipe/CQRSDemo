package com.example.demo.events;

public class UserCreatedEvent extends Event {
    private String userId;
    private String firstName;
    private String lastName;

    public UserCreatedEvent(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
