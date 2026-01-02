package com.codehills.car_management.backend.dto;

import java.util.Date;

public record ErrorDetailsDto(
        Date timestamp,
        String message,
        String details
) { }
