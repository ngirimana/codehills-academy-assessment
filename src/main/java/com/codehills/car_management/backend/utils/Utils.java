package com.codehills.car_management.backend.utils;

import com.codehills.car_management.backend.models.FuelEntry;

import java.util.List;

public class Utils {
    public static double getOdometer(List<FuelEntry> fuelEntries,OdometerLevel level) {
        if(level == OdometerLevel.MIN) {
            return fuelEntries.stream()
                    .mapToDouble(FuelEntry::getOdometer)
                    .min()
                    .orElse(0);
        }
        else if(level == OdometerLevel.MAX) {
            return fuelEntries.stream()
                    .mapToDouble(FuelEntry::getOdometer)
                    .max()
                    .orElse(0);
        }
       return 0;
    }
}
