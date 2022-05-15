package com.example.securitygateway.exception;

import com.example.securitygateway.entity.ExceptionEntity;
import com.example.securitygateway.exception.exceptions.JwtAuthException;
import com.example.securitygateway.exception.exceptions.UserNotFoundException;
import io.jsonwebtoken.Jwt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ExceptionEntity> handleNotFoundUser(Exception ex) {
        List<String> errorMessages = List.of(ex.getMessage());
        ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .status(HttpStatus.NOT_FOUND)
                .code(HttpStatus.NOT_FOUND.value())
                .messages(errorMessages)
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionEntity);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<ExceptionEntity> handlePSQL(SQLException ex) {
        List<String> errorMessages = List.of(ex.getLocalizedMessage());
        ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .messages(errorMessages)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionEntity);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionEntity> handleEmptyResult(EmptyResultDataAccessException ex) {
        List<String> errorMessages = List.of(ex.getLocalizedMessage());
        ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .messages(errorMessages)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionEntity);
    }

    @ExceptionHandler({JwtAuthException.class})
    public ResponseEntity<ExceptionEntity> handleJwtToken(JwtAuthException e) {
        List<String> errorMessages = List.of(e.getLocalizedMessage());
        ExceptionEntity exceptionEntity = ExceptionEntity.builder()
                .status(e.getStatus())
                .code(e.getStatus().value())
                .messages(errorMessages)
                .build();
        return ResponseEntity
                .status(e.getStatus())
                .body(exceptionEntity);
    }

}
