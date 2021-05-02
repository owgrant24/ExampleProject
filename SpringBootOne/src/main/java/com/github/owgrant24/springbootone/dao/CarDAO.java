package com.github.owgrant24.springbootone.dao;

import com.github.owgrant24.springbootone.model.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getAllCars();

    List<Car> getAllCarsWithFilter();

    void saveCar(Car car);

    Car getCarById(int id);

    void deleteCarById(int id);
}
