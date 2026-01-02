package com.codehills.car_management.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateCarDto(
        @NotBlank(message = "brand is required") String brand,
        @NotBlank(message = "model is required") String model,
        @Min(value = 1970, message = "year must be >= 1970")
        @Max(value = 2026, message = "year must be <= 2026")
        int year
) {}
