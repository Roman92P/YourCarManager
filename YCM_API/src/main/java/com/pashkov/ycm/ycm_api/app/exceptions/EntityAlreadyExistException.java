package com.pashkov.ycm.ycm_api.app.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityAlreadyExistException extends ResponseEntityExceptionHandler {

}
