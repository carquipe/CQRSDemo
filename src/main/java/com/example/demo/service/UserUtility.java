package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.projectors.UserProjector;
import com.example.demo.repositories.EventStore;
import com.example.demo.repositories.UserReadRepository;
import com.example.demo.repositories.UserWriteRepository;

public class UserUtility {
    private static UserReadRepository readRepository;
    private static UserWriteRepository writeRepository;

    public UserUtility(UserReadRepository readRepository, UserWriteRepository writeRepository) {
        this.readRepository = readRepository;
        this.writeRepository = writeRepository;
    }

    public static User recreateUserState(EventStore repository, String userId) {
        UserProjector projector = new UserProjector(readRepository);
        projector.project(userId, repository.getUserEvent(userId));
        return writeRepository.getUser(userId);
    }
}
