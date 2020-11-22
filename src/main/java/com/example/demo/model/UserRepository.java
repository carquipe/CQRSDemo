package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> store = new HashMap<>();

    public User getUser(String userId) {
        return store.get(userId);
    }

    public void addUser(String userId, User user) {
        store.put(userId, user);
    }
}