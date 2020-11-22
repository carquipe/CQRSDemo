package com.example.demo.service;

import com.example.demo.commands.CreateUserCommand;
import com.example.demo.commands.UpdateUserCommand;
import com.example.demo.events.*;
import com.example.demo.model.Address;
import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repositories.EventStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserAggregate {
    private EventStore writeRepository;

    public UserAggregate(EventStore writeRepository) {
        this.writeRepository = writeRepository;
    }

    public List<Event> handleCreateUserCommand(CreateUserCommand command) {
        UserCreatedEvent event =
                new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
        writeRepository.addEvent(command.getUserId(), event);

        return Collections.singletonList(event);
    }

    public List<Event> handleUpdateUserCommand(UpdateUserCommand command) {
        User user = UserUtility.recreateUserState(writeRepository, command.getUserId());
        List<Event> events = new ArrayList<>();

        List<Contact> contactsToRemove = user.getContacts().stream()
                .filter(c -> !command.getContacts().contains(c))
                .collect(Collectors.toList());
        for (Contact contact : contactsToRemove) {
            UserContactRemovedEvent contactRemovedEvent =
                    new UserContactRemovedEvent(contact.getType(), contact.getDetail());
            events.add(contactRemovedEvent);
            writeRepository.addEvent(command.getUserId(), contactRemovedEvent);
        }
        List<Contact> contactsToAdd = command.getContacts().stream()
                .filter(c -> !user.getContacts().contains(c))
                .collect(Collectors.toList());
        for (Contact contact : contactsToAdd) {
            UserContactAddedEvent contactAddedEvent = new UserContactAddedEvent(contact.getType(), contact.getDetail());
            events.add(contactAddedEvent);
            writeRepository.addEvent(command.getUserId(), contactAddedEvent);
        }

        List<Address> addressesToRemove = user.getAddresses().stream()
                .filter(c -> !command.getAddresses().contains(c))
                .collect(Collectors.toList());

        for (Address address : addressesToRemove) {
            UserAddressRemovedEvent addressRemovedEvent = new UserAddressRemovedEvent(address.getCity(),
                    address.getState(), address.getPostcode());
            events.add(addressRemovedEvent);
            writeRepository.addEvent(command.getUserId(), addressRemovedEvent);
        }
        List<Address> addressesToAdd = command.getAddresses().stream()
                .filter(c -> !user.getAddresses().contains(c))
                .collect(Collectors.toList());
        for (Address address : addressesToAdd) {
            UserAddressAddedEvent addressAddedEvent = new UserAddressAddedEvent(address.getCity(), address.getState(),
                    address.getPostcode());
            events.add(addressAddedEvent);
            writeRepository.addEvent(command.getUserId(), addressAddedEvent);
        }

        return events;
    }
}