package com.example.jpa.service;

import com.example.jpa.UserClassSpringBoot;
import com.example.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
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
