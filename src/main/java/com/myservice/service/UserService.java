package com.myservice.service;

import com.myservice.model.User;

public interface UserService {
    long create(User user);

    User findUserById(long id);
}
