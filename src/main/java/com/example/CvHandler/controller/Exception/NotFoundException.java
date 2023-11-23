package com.example.CvHandler.controller.Exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    HttpStatus httpStatus;
    public NotFoundException(String message,HttpStatus status){
        super(message);
        this.httpStatus = status;

    }
}