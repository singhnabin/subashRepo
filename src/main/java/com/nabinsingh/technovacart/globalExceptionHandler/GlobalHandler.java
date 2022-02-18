package com.nabinsingh.technovacart.globalExceptionHandler;

import com.nabinsingh.technovacart.exception.AuthenticationFailed;
import com.nabinsingh.technovacart.exception.UserNotFoundException;
import com.nabinsingh.technovacart.response.ApiResponse;
import com.nabinsingh.technovacart.response.AppContstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<FieldError> errorList = ex.getFieldErrors();
        List<String> erorrs= new ArrayList<>();
        for(FieldError err: errorList) {
            erorrs.add(err.getDefaultMessage());
            log.error("Error is : {}",err.getDefaultMessage());

        }
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST, AppContstant.REQUESTFAILED,null,erorrs);
    }

    @ExceptionHandler({UserNotFoundException.class, AuthenticationFailed.class})
    public ResponseEntity<Object> handleUserNameNotfoundException(Exception ex){
        log.error("Exception caught with message: {}",ex);
//        if(ex instanceof UserNotFoundException){
//
//        }
        return ApiResponse.generateResponse(HttpStatus.BAD_REQUEST,AppContstant.REQUESTFAILED,null,ex.getMessage());

    }


}
