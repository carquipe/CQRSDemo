package com.example.demo.repositories;

import com.example.demo.model.UserAddress;
import com.example.demo.model.UserContact;

import java.util.HashMap;
import java.util.Map;

public class UserReadRepository {
    private Map<String, UserAddress> userAddress = new HashMap<>();
    private Map<String, UserContact> userContact = new HashMap<>();

    public UserContact getUserContact(String userId) {
        return userContact.get(userId);
    }

    public UserAddress getUserAddress(String userId) {
        return userAddress.get(userId);
    }

    public void addUserAddress(String userid, UserAddress userAddress) {
        this.userAddress.put(userid, userAddress);
    }

    public void addUserContact(String userid, UserContact userContact) {
        this.userContact.put(userid, userContact);
    }
}
