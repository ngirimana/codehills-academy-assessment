package com.codehills.car_management.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FuelEntry {
    private double liters;
    private double price;
    private double odometer;
}