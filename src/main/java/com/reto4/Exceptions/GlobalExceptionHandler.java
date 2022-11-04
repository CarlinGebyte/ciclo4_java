package com.reto4.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseCustomExceptionUser>basicCustomException(BaseCustomException ex) {
        return ResponseEntity.badRequest().body(BaseCustomExceptionUser.builder()
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build());
    }
}
