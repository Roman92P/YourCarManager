package com.pashkov.ycm.ycm_api.YCM_API.app.exceptions;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Roman Pashkov created on 03.01.2023 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
@Data
public class ApiError {

    private int status;
    private String message;
    private List<String> errors;

    public ApiError(int status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
    public ApiError(int status, String message, String... error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.stream(error).collect(Collectors.toList());
    }
}
