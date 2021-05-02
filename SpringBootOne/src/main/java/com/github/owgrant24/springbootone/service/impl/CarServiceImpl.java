package com.github.owgrant24.springbootone.service.impl;

import com.github.owgrant24.springbootone.model.Car;
import com.github.owgrant24.springbootone.repository.CarRepository;
import com.github.owgrant24.springbootone.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    @Transactional
    public Car getCarById(int id) {
        return carRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Car> getAllCarsWithFilter(Boolean text) {
        return carRepository.getAllCarsWithFilter(text);
    }

}
