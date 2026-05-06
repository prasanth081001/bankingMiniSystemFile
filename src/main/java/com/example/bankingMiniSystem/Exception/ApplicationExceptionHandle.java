package com.example.bankingMiniSystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleError(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
