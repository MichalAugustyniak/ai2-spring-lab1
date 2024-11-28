package com.ai2.ai2lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> notFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiException exception = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, status);
    }
}
