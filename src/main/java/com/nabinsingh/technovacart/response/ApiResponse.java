package com.nabinsingh.technovacart.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    public static ResponseEntity<Object> generateResponse(HttpStatus status,String message,Object data, Object error){
        Map<String, Object> resp= new HashMap<>();
        resp.put(AppContstant.STATUSCODE,status.value());
        resp.put("message",message);
        resp.put("data",data);
        resp.put("errors",error);
        return new ResponseEntity<>(resp,status);
    }
}
