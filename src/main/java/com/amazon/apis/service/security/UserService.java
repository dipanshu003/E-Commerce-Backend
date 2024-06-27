package com.amazon.apis.service.security;

import com.amazon.apis.model.User;

import jakarta.annotation.PostConstruct;

public interface UserService {
    boolean createUser(User user);

    @PostConstruct
    void createAdminAccount();

}
