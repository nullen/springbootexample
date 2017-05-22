package com.myservice.service.impl;

import com.myservice.dao.UserDao;
import com.myservice.model.User;
import com.myservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public long create(User user) {
        return this.userDao.create(user);
    }

    @Override
    public User findUserById(long id) {
        return this.userDao.getUserById(id);
    }
}
