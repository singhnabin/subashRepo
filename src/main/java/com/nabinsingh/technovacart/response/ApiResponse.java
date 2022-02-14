package com.nabinsingh.technovacart.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    public static ResponseEntity<Object> generateResponse(HttpStatus status,String message,Object data, Object error){
        Map<String, Object> resp= new HashMap<>();
        resp.put("statusCode",status.value());
        resp.put("messgage",message);
        resp.put("data",data);
        resp.put("errors",error);
        return new ResponseEntity<>(resp,status);
    }
}
