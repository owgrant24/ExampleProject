package com.github.owgrant24.one.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Size(min = 1, max = 50, message = "Model should be from 1 to 50 symbols")
    @Column(name = "model")
    private String model;

    @Size(min = 1, max = 50, message = "Brand should be from 1 to 50 symbols")
    @Column(name = "brand")
    private String brand;

    @Column(name = "year_of_manufacture")
    private int year;

    @Override
    public String toString() {
        return brand + " " + model + " " + year;
    }
}
