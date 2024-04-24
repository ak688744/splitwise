package com.splitwise.demo.controller;

import com.splitwise.demo.entities.UserEntity;
import com.splitwise.demo.models.CreateUser;
import com.splitwise.demo.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired UserRegistrationService userRegistrationService;
    @PostMapping
    public ResponseEntity<? extends Object> createUser(@RequestBody CreateUser createUser){
        try {
            return ResponseEntity.ok(userRegistrationService.addUser(createUser.getUserName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
