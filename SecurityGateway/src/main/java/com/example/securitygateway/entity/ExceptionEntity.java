package com.example.securitygateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ExceptionEntity {

    private HttpStatus status;
    private int code;
    private List<String> messages;
}
