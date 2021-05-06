package com.github.owgrant24.springbootone.service.impl;

import com.github.owgrant24.springbootone.model.Car;
import com.github.owgrant24.springbootone.repository.CarRepository;
import com.github.owgrant24.springbootone.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
        return carRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Page<Car> getCarWitPagingAndFiltering(Specification<Car> specification, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return carRepository.findAll(specification, PageRequest.of(page - 1, 10));
    }

    @Override
    @Transactional
    public List<Car> getAllCarsWithFilter(Boolean text) {
        return carRepository.getAllCarsWithFilter(text);
    }

}
