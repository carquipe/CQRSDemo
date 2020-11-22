package com.example.demo.repositories;

import com.example.demo.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStore {
    private Map<String, List<Event>> store = new HashMap<>();

    public void addEvent(String userId, Event event) {
        List<Event> userEvents = store.get(userId);
        userEvents.add(event);
        store.put(userId, userEvents);
    }

    public Map<String, List<Event>> getStore() {
        return store;
    }

    public List<Event> getUserEvent(String userId) {
        return store.get(userId);
    }
}
