package com.myservice.rest;

import com.myservice.model.User;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {
    @Getter
    private User user;

    public UserResource(User user) {
        this.user = user;
    }
}
