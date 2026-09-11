package com.eComm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.eComm.user.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = UserException.class)
    public ResponseEntity<?> handleUserException(UserException exception) {

        return new ResponseEntity<>(
                new ApiResponse<>(
                        exception.getMessage(),
                        null,
                        exception.getHttpStatus()
                ),
                exception.getHttpStatus()
        );
    }
}