package com.example.CvHandler.controller.Exception;


import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handelNotFoundException(@NotNull NotFoundException exception){
        log.error(NotFoundException.class.getName() + " " +  exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(),exception.httpStatus);
    }


}
