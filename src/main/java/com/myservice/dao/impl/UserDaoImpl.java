package com.myservice.dao.impl;

import com.myservice.dao.UserDao;
import com.myservice.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long create(User user) {
        this.entityManager.persist(user);
        return user.getId();
    }

    @Override
    public long update(User user) {
        this.entityManager.merge(user);
        return user.getId();
    }

    @Override
    public User getUserById(long id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public void delete(long id) {
        User user = getUserById(id);
        if (user != null) {
            this.entityManager.refresh(user);
        }
    }
}
