package com.myproject.employeedirectory;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationError {
    private List<String> errors = new ArrayList<>();

    public void addValidationError(String error) {
        errors.add(error);
    }
}
