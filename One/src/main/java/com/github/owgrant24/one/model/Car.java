package com.github.owgrant24.one.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 1, max = 50, message = "Model should be from 1 to 50 symbols")
    @Column(name = "model")
    private String model;

    @Size(min = 1, max = 50, message = "Brand should be from 1 to 50 symbols")
    @Column(name = "brand")
    private String brand;

    @Column(name = "year_of_manufacture")
    private int year;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
