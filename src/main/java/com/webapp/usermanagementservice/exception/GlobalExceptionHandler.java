package com.webapp.usermanagementservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(ex.getMessage())
                        .error(ErrorMessage.EXCEPTION)
                        .build());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(ex.getMessage())
                        .error(ErrorMessage.ALREADY_EXISTS)
                        .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .uuid(UUID.randomUUID())
                        .timestamp(LocalDateTime.now())
                        .message(ex.getMessage())
                        .error(ErrorMessage.USER_NOT_FOUND)
                        .build());
    }

}
