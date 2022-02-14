package com.nabinsingh.technovacart.controller;

import com.nabinsingh.technovacart.model.User;
import com.nabinsingh.technovacart.response.ApiResponse;
import com.nabinsingh.technovacart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Object getAllUsers(@RequestParam(name = "email",required = false) String email){
        if(email!=null){
            Optional<User> user=userService.getUserByEmail(email);
           return ApiResponse.generateResponse(HttpStatus.OK,"User with email "+email+" fetched.",user,null);


        }
       List<User> userList=userService.getAllUsers();
        log.info("{} {} available",userList.size(),"users");
        if(userList.size()>0){
            return ApiResponse.generateResponse(HttpStatus.OK,userList.size()+" users with available",userList,null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND,"No users available",null,"User not available");

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        Optional<User> existingUser= userService.getUserById(id);
        if(!existingUser.isPresent()){
            log.error("User with id {} not found",id);
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST,"Request Failed",null,"User with id "+id+" does not exists");
        }
        log.error("User with id {}  found",id);
        return ApiResponse.generateResponse(HttpStatus.OK,"User with id "+id +"is available ",existingUser,null);
    }

    //update mapping
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> existingUser= userService.getUserById(id);
        if(existingUser.isPresent()){
            User updateUser=userService.updateUser(existingUser.get(),user);
            return ApiResponse.generateResponse(HttpStatus.OK,"User updated successfully",updateUser,null);
        }

        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND,"User doesnot present",null,"user with id "+id+"not found");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        Optional<User> existingUser= userService.getUserById(id);
        if(!existingUser.isPresent()){
            log.error("User with id {} not found",id);
            return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST,"Request Failed",null,"User with id "+id+" does not exists");
        }
        userService.deleteUser(id);
        log.info("User with id  {} deleted succcessfully",id);
        return ApiResponse.generateResponse(HttpStatus.OK,"deleted succcessfully",null,null);
    }
}
