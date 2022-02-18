package com.nabinsingh.technovacart.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "email cannot be blank")
    private String password;
}
