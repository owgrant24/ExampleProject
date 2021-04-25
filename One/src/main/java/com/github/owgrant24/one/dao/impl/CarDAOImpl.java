package com.github.owgrant24.one.dao.impl;

import com.github.owgrant24.one.dao.CarDAO;
import com.github.owgrant24.one.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Car> getAllCars() {
        Session session = sessionFactory.getCurrentSession();
        Query<Car> query = session.createQuery("FROM Car", Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCarsWithFilter(int value, String column) {
        Session session = sessionFactory.getCurrentSession();
        Query<Car> query = session.createQuery(
                "FROM Car WHERE " + column + "=:text");
        query.setParameter("text", value);
        return query.getResultList();
    }

    @Override
    public List<Car> getAllCarsWithTwoFilter(int value, String text2, String column, String column2) {
        Session session = sessionFactory.getCurrentSession();
        Query<Car> query = session.createQuery(
                "FROM Car WHERE " + column + "=:value AND " + column2 + "=:text2");
        query.setParameter("value", value);
        query.setParameter("text2", text2);
        return query.getResultList();
    }

    @Override
    public void saveCar(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(car);
    }

    @Override
    public Car getCarById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Car.class, id);
    }

    @Override
    public void deleteCarById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Car> query = session.createQuery(
                "DELETE FROM Car WHERE id=:carId");
        query.setParameter("carId", id);
        query.executeUpdate();
    }
}
