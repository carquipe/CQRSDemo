package com.example.demo.service;

import com.example.demo.commands.AddressByRegionQuery;
import com.example.demo.commands.ContactByTypeQuery;
import com.example.demo.model.Address;
import com.example.demo.model.Contact;
import com.example.demo.model.UserAddress;
import com.example.demo.model.UserContact;
import com.example.demo.repositories.UserReadRepository;

import java.util.Set;

public class UserProjection {
    private UserReadRepository readRepository;

    public UserProjection(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public Set<Contact> handle(ContactByTypeQuery query) {
        UserContact userContact = readRepository.getUserContact(query.getUserId());
        return userContact.getContactByType().get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) {
        UserAddress userAddress = readRepository.getUserAddress(query.getUserId());
        return userAddress.getAddressByRegion().get(query.getState());
    }
}
