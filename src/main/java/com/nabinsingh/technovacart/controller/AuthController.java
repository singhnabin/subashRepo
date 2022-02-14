package com.nabinsingh.technovacart.controller;


import com.nabinsingh.technovacart.model.User;
import com.nabinsingh.technovacart.response.ApiResponse;
import com.nabinsingh.technovacart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;



    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
       Optional<User> existingUser= userService.getUserByEmail(user.getEmail());
       if(existingUser.isPresent()){
           return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST,"Request Failed",null,"User with emai "+user.getEmail()+" already exists");
       }
       User savedUser =userService.createUser(user);

       return ApiResponse.generateResponse(HttpStatus.CREATED,"User created Successfully",savedUser,null);

    }


}
