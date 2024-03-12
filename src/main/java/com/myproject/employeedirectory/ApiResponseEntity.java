package com.myproject.employeedirectory;

import lombok.Data;

@Data
public class ApiResponseEntity {
    private String message;

    public ApiResponseEntity(String message) {
        this.message = message;
    }
}
