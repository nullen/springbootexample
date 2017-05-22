package com.myservice.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserNotFoundException extends Exception {
    @Getter
    private long userId;
}
