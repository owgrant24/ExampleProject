package com.github.owgrant24.springbootone.dao.impl;

import com.github.owgrant24.springbootone.dao.RoleDAO;
import com.github.owgrant24.springbootone.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role getOne(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Role.class, id);
    }
}
