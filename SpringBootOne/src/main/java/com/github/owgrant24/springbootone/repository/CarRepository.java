package com.github.owgrant24.springbootone.repository;

import com.github.owgrant24.springbootone.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

    @Query(value = "FROM Car WHERE sold = ?1")
    List<Car> getAllCarsWithFilter(Boolean text);

}
