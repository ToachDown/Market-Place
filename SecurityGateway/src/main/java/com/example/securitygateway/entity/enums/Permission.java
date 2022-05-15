package com.example.securitygateway.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;

}
