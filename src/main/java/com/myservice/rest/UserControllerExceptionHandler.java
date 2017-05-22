package com.myservice.rest;

import com.myservice.rest.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public VndErrors serverErrorException(UserNotFoundException ex) {
        log.error("UserId '{}' not found.", ex.getUserId());
        return new VndErrors(ex.getClass().getName(), String.format("User with userId %s can not be found.", ex.getUserId()));
    }
}
