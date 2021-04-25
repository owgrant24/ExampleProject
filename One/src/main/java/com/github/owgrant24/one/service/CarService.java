package com.github.owgrant24.one.service;

import com.github.owgrant24.one.model.Car;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    List<Car> getAllCarsWithFilter(int value, String column);

    List<Car> getAllCarsWithTwoFilter(int value , String text2, String column, String column2);

    void saveCar(Car car);

    Car getCarById(int id);

    void deleteCarById(int id);
}
