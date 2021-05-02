package com.github.owgrant24.springbootone.dao.impl;

import com.github.owgrant24.springbootone.dao.CarDAO;
import com.github.owgrant24.springbootone.model.Car;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Car> getAllCars() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("FROM Car", Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCarsWithFilter() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(
                "FROM Car WHERE sold =:text");
        query.setParameter("text", false);
        return query.getResultList();
    }

    @Override
    public void saveCar(Car car) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(car);
    }

    @Override
    public Car getCarById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Car.class, id);
    }

    @Override
    public void deleteCarById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(
                "DELETE FROM Car WHERE id=:carId");
        query.setParameter("carId", id);
        query.executeUpdate();
    }
}