package com.example.pitang.user.infrastructure.config.exceptions;

import com.example.pitang.user.infrastructure.config.exceptions.user.EmailAlreadyExistsException;
import com.example.pitang.user.infrastructure.config.exceptions.user.InvalidFieldsException;
import com.example.pitang.user.infrastructure.config.exceptions.user.LoginAlreadyExistsException;
import com.example.pitang.user.infrastructure.config.exceptions.user.MissingFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final String MISSING_FIELDS_MESSAGE = "Missing fields";
    public static final String INVALID_FIELDS_MESSAGE = "Invalid fields";

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleLoginAlreadyExistsException(LoginAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        RuntimeException response = new RuntimeException();

        boolean hasMissingFields = false;
        boolean hasInvalidFields = false;

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            if (Objects.requireNonNull(error.getDefaultMessage()).contains(MISSING_FIELDS_MESSAGE)) {
                hasMissingFields = true;
            }
            if (error.getDefaultMessage().contains(INVALID_FIELDS_MESSAGE)) {
                hasInvalidFields = true;
            }
        }

        if (hasMissingFields) {
            response = new MissingFieldsException();
        }
        else if (hasInvalidFields) {
            response = new InvalidFieldsException();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(response.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
