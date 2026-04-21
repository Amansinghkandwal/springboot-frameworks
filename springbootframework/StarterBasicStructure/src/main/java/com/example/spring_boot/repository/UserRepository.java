package com.example.spring_boot.repository;

import com.example.spring_boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{
       List<User> findByNameAndEmail(String name, String email);
}
