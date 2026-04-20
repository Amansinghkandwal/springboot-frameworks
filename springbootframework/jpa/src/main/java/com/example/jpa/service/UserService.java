package com.example.jpa.service;

import com.example.jpa.UserClassSpringBoot;
import com.example.jpa.repository.UserRepository;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    public UserClassSpringBoot createUser(UserClassSpringBoot userClassSpringBoot)
    {
          userRepository.save(userClassSpringBoot);
          return userClassSpringBoot;
    }
}
