package com.codehills.car_management.backend.dto;

import jakarta.validation.constraints.Positive;

public record AddFuelDto(
        @Positive double liters,
        @Positive double pricePerLiter,
        @Positive double odometer
) { }
