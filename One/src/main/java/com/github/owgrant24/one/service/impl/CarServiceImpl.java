package com.github.owgrant24.one.service.impl;

import com.github.owgrant24.one.dao.CarDAO;
import com.github.owgrant24.one.model.Car;
import com.github.owgrant24.one.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    @Autowired
    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    @Transactional
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    @Transactional
    public List<Car> getAllCarsWithFilter(int value, String column) {
        return carDAO.getAllCarsWithFilter(value, column);
    }

    @Override
    @Transactional
    public List<Car> getAllCarsWithTwoFilter(int value, String text2, String column, String column2) {
        return carDAO.getAllCarsWithTwoFilter(value, text2, column, column2);
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        carDAO.saveCar(car);
    }

    @Override
    @Transactional
    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }

    @Override
    @Transactional
    public void deleteCarById(int id) {
        carDAO.deleteCarById(id);
    }
}
