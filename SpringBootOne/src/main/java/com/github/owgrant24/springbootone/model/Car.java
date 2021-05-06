package com.github.owgrant24.springbootone.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 7, max = 20, message = "VIN should be from 7 to 20 symbols")
    @Column(name = "vin", unique = true)
    private String vin;

    @Size(min = 1, max = 50, message = "Model should be from 1 to 50 symbols")
    @Column(name = "model")
    private String model;

    @Size(min = 1, max = 50, message = "Brand should be from 1 to 50 symbols")
    @Column(name = "brand")
    private String brand;

    @Min(value = 1850, message = "Must be at least 1850")
    @Max(value = 2050, message = "Should be no more than 2050")
    @Column(name = "year_of_manufacture")
    private int year;

    @Column(name = "price")
    private int price;

    @Column(name = "mileage")
    private int mileage;

    @Column(name = "selling_price")
    private Integer sellingPrice;

    @Column(name = "sold")
    private boolean sold;

    @Override
    public String toString() {
        return brand + " " + model + " " + year;
    }
}
