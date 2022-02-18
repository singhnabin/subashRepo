package com.nabinsingh.technovacart.controller;


import com.nabinsingh.technovacart.model.Response;
import com.nabinsingh.technovacart.model.User;
import com.nabinsingh.technovacart.response.ApiResponse;
import com.nabinsingh.technovacart.response.AppContstant;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/main")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/orders")
    public ResponseEntity<Object> getAllOrder(){
       ResponseEntity<Response> responseEntity=restTemplate.getForEntity("http://localhost:8081/api/orders", Response.class);
//
       if (responseEntity.getStatusCode()== HttpStatus.OK){
           Response responseBody= responseEntity.getBody();
//          // "{ "data": [ { "id": 1, "firstName": "Nabin", "lastName": "singh", "email": "nabin1234" }, { "id": 11, "firstName": "nabin", "lastName": "Singh", "email": "nabin12345@gmail.com" }, { "id": 12, "firstName": "nabin", "lastName": "Singh", "email": "nabin12345@gmail" } ], "messgage": "3 users with available", "errors": null, "statusCode": 200 }"
//           JSONObject resp= new JSONObject(responseBody);
//           Object data=resp.get("data");
//           String message=resp.getString("message");
//
//           System.out.println(responseBody);
           return new ResponseEntity<>(responseBody,HttpStatus.OK);
       }


       return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, AppContstant.REQUESTFAILED,null,AppContstant.REQUESTFAILED);


    }
}
