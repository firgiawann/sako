package com.klp4.sako.controller;

import com.klp4.sako.model.User;
import com.klp4.sako.service.UserService;

public class AuthController {
    private UserService userService;

    public AuthController() {
        this.userService = new UserService();
    }

    public User login(String username, String password) {
        return userService.authenticate(username, password);
    }
}
