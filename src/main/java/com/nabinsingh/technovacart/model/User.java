package com.nabinsingh.technovacart.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_db")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private  String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

}
