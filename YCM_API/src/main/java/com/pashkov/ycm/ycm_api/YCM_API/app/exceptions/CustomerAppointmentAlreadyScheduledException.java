package com.pashkov.ycm.ycm_api.YCM_API.app.exceptions;

/**
 * Roman Pashkov created on 27.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
public class CustomerAppointmentAlreadyScheduledException extends RuntimeException{

    public CustomerAppointmentAlreadyScheduledException(String errorMessage) {
        super(errorMessage);
    }
}
