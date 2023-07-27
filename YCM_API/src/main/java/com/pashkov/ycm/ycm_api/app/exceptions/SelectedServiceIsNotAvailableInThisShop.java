package com.pashkov.ycm.ycm_api.app.exceptions;

/**
 * Roman Pashkov created on 29.11.2022 inside the package - com.pashkov.ycm.ycm_api.YCM_API.app.exceptions
 */
public class SelectedServiceIsNotAvailableInThisShop extends RuntimeException {

    public SelectedServiceIsNotAvailableInThisShop(String message) {
        super(message);
    }
}
