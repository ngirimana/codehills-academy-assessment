package com.codehills.car_management.backend.dto;

public record FuelStats(
        double totalFuel,
        double totalCost,
        double averageConsumptionPer100Km
) {}
