package com.example.demo.projectors;

import com.example.demo.events.*;
import com.example.demo.model.Address;
import com.example.demo.model.UserAddress;
import com.example.demo.repositories.UserReadRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserProjector {
    UserReadRepository readRepository;

    public UserProjector(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public void project(String userId, List<Event> events) {
        for (Event event : events) {
            if (event instanceof UserAddressAddedEvent)
                apply(userId, (UserAddressAddedEvent) event);
            if (event instanceof UserAddressRemovedEvent)
                apply(userId, (UserAddressRemovedEvent) event);
            if (event instanceof UserContactAddedEvent)
                apply(userId, (UserContactAddedEvent) event);
            if (event instanceof UserContactRemovedEvent)
                apply(userId, (UserContactRemovedEvent) event);
        }
    }

    public void apply(String userId, UserAddressAddedEvent event) {
        Address address = new Address(event.getCity(), event.getState(), event.getPostCode());
        UserAddress userAddress = Optional.ofNullable(readRepository.getUserAddress(userId))
                .orElse(new UserAddress());
        Set<Address> addresses = Optional.ofNullable(userAddress.getAddressByRegion().get(address.getState()))
                .orElse(new HashSet<>());
        addresses.add(address);
        userAddress.getAddressByRegion().put(address.getState(), addresses);
        readRepository.addUserAddress(userId, userAddress);
    }

    public void apply(String userId, UserAddressRemovedEvent event) {
        Address address = new Address(event.getCity(), event.getState(), event.getPostcode());
        UserAddress userAddress = readRepository.getUserAddress(userId);
        if (userAddress != null) {
            Set<Address> addresses = userAddress.getAddressByRegion().get(address.getState());
            if (addresses != null)
                addresses.remove(address);
            readRepository.addUserAddress(userId, userAddress);
        }
    }

    public void apply(String userId, UserContactAddedEvent event) {
        // Similarly handle UserContactAddedEvent event
    }

    public void apply(String userId, UserContactRemovedEvent event) {
        // Similarly handle UserContactRemovedEvent event
    }
}

