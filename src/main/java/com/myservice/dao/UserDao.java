package com.myservice.dao;

import com.myservice.model.User;

public interface UserDao {
    long create(User user);

    long update(User user);

    User getUserById(long id);

    void delete(long id);
}
