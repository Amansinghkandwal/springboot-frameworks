package com.example.jpa.repository;

import com.example.jpa.UserClassSpringBoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserClassSpringBoot,Long> {

}
