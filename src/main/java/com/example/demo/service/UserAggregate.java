package com.example.demo.service;

import com.example.demo.commands.CreateUserCommand;
import com.example.demo.commands.UpdateUserCommand;
import com.example.demo.model.User;
import com.example.demo.repositories.UserWriteRepository;

public class UserAggregate {
    private UserWriteRepository writeRepository;

    public UserAggregate(UserWriteRepository writeRepository) {
        this.writeRepository = writeRepository;
    }

    public User handleCreateUserCommand(CreateUserCommand command) {
        User user = new User(command.getUserId(), command.getFirstName(), command.getLastName());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }

    public User handleUpdateUserCommand(UpdateUserCommand command) {
        User user = writeRepository.getUser(command.getUserId());
        user.setAddresses(command.getAddresses());
        user.setContacts(command.getContacts());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }
}