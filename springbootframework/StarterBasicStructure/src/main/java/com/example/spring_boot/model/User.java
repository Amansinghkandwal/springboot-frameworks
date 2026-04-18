package com.example.spring_boot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

//POJO class -> Plain old java object
@Getter
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private String email;

}
