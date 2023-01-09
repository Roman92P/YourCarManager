package com.pashkov.ycm.ycm_api.YCM_API.app.exceptions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Roman Pashkov created on 27.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
public class CustomerAppointmentAlreadyScheduledException extends RuntimeException{

    public CustomerAppointmentAlreadyScheduledException(String errorMessage) {
        super(errorMessage);
    }

    public CustomerAppointmentAlreadyScheduledException(String... errorMessage) {
        super(String.join(",", errorMessage));
    }

    public CustomerAppointmentAlreadyScheduledException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public CustomerAppointmentAlreadyScheduledException(){
        super();
    }
}
