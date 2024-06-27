package com.amazon.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.apis.model.User;
import com.amazon.apis.service.security.UserService;

@RestController
@CrossOrigin
public class SignUpController {

    @Autowired
    private UserService service;

    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUpUser(@RequestBody User user) {
        boolean isCreated = service.createUser(user);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(isCreated);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isCreated);
    }
}
