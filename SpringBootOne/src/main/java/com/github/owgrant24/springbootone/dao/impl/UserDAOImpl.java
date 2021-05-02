package com.github.owgrant24.springbootone.dao.impl;

import com.github.owgrant24.springbootone.dao.UserDAO;
import com.github.owgrant24.springbootone.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override
    public User getUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(
                "FROM User WHERE email=:email");
        query.setParameter("email", email);
        return query.list().stream().findAny().orElse(null);
    }

    @Override
    public void deleteUserById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<User> query = session.createQuery(
                "DELETE FROM User WHERE id=:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }
}
