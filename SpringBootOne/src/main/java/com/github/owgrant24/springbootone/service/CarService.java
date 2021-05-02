package com.github.owgrant24.springbootone.service;

import com.github.owgrant24.springbootone.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    List<Car> getAllCarsWithFilter(Boolean text);

    void saveCar(Car car);

    Car getCarById(int id);

    void deleteCarById(int id);
}
