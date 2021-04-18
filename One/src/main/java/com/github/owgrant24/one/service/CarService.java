package com.github.owgrant24.one.service;

import com.github.owgrant24.one.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    void saveCar(Car car);

    Car getCarById(int id);

    void deleteCarById(int id);
}
