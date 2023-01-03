package com.pashkov.ycm.ycm_api.YCM_API.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Roman Pashkov created on 03.01.2023 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
@ControllerAdvice
public class YcmApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerAppointmentAlreadyScheduledException.class})
    protected ResponseEntity<ApiError> handleCustomerAppointmentAlreadyScheduledException
            (CustomerAppointmentAlreadyScheduledException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
        return ResponseEntity.badRequest().body(apiError);
    }
}
