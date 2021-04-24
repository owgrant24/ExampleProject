package com.github.owgrant24.one.service;

import com.github.owgrant24.one.model.Car;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    List<Car> getAllCarsWithFilter(String filter);

    void saveCar(Car car);

    Car getCarById(int id);

    void deleteCarById(int id);
}
