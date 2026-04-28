package com.klp4.sako.service;

import com.klp4.sako.model.User;
import com.klp4.sako.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User authenticate(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
