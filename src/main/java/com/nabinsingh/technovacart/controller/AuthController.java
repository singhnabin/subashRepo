package com.nabinsingh.technovacart.controller;


import com.nabinsingh.technovacart.model.LoginRequest;
import com.nabinsingh.technovacart.model.User;
import com.nabinsingh.technovacart.response.ApiResponse;
import com.nabinsingh.technovacart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;



    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
//        if (result.hasFieldErrors()) {
//            List<FieldError> errorList = result.getFieldErrors();
//            for (FieldError err : errorList) {
//                System.out.println("error: " + err.getDefaultMessage());
//            }
//
//        } else {
            Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, "Request Failed", null, "User with emai " + user.getEmail() + " already exists");
            }
            User savedUser = userService.createUser(user);

            return ApiResponse.generateResponse(HttpStatus.CREATED, "User created Successfully", savedUser, null);

//        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> userLogin(@RequestBody @Valid LoginRequest loginRequest){
             return  ApiResponse.generateResponse(HttpStatus.OK,"Login Successful",userService.userLogin(loginRequest),null);

    }


}
