package com.pashkov.ycm.ycm_api.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

/**
 * Roman Pashkov created on 03.01.2023 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
@ControllerAdvice
public class YcmApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {CustomerAppointmentAlreadyScheduledException.class, DateHourForSelectedServiceIsNotAvailable.class})
    protected ApiError handleCustomerAppointmentAlreadyScheduledException
            (Exception ex) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ApiError handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), String.valueOf(HttpStatus.NOT_FOUND));
    }
}
