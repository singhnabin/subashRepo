package com.nabinsingh.technovacart.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_db")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 8, message = "firstname should be between 3 to 8 character")
    private  String firstName;

    @Column
    @NotBlank(message = "Lastname is required")
    private String lastName;

    @Column
    @Email(message = "should be a proper email")
    private String email;

    @Column
    @NotBlank(message = "Password cannot be blank")
    private String password;



}
