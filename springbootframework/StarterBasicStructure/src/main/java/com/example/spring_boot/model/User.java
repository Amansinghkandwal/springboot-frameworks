package com.example.spring_boot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//POJO class -> Plain old java object
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Integer id;
    private String name;
    private String email;

}
