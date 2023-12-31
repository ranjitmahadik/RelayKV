package com.dkv.distributedkvstore.exceptions;

import com.dkv.distributedkvstore.keyvaluestore.exceptions.KeyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {KeyNotFoundException.class})
    public ResponseEntity<?> keyNotFoundExceptionHandler(KeyNotFoundException keyNotFoundException) {
        ExceptionResponse response = new ExceptionResponse(keyNotFoundException.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
