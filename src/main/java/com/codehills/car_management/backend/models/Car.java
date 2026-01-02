package com.codehills.car_management.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Long id;
    private String brand;
    private String model;
    private int year;
}
