package com.example.jpa.controller;

import com.example.jpa.UserClassSpringBoot;
import com.example.jpa.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;


    private UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping
    public UserClassSpringBoot createUser(@RequestBody UserClassSpringBoot userClassSpringBoot)
    {
        return userService.createUser(userClassSpringBoot);
    }
}
