package com.example.demo.events;

public class UserAddressRemovedEvent extends Event {
    private String city;
    private String state;
    private String postcode;

    public UserAddressRemovedEvent(String city, String state, String postcode) {
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }
}
