package com.lpdouglas.alugalivro.handler;

import com.lpdouglas.alugalivro.exception.LivroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LivroExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidUser(LivroException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
