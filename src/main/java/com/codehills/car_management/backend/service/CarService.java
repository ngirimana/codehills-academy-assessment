package com.codehills.car_management.backend.service;

import com.codehills.car_management.backend.dto.AddFuelDto;
import com.codehills.car_management.backend.dto.CreateCarDto;
import com.codehills.car_management.backend.dto.FuelStats;
import com.codehills.car_management.backend.models.Car;
import com.codehills.car_management.backend.models.FuelEntry;
import java.util.*;

public interface CarService {
    public Car addCar(CreateCarDto carDto);
    public List<Car> getAllCars();
    public Optional<Car> getCarById(Long carId);
    public FuelEntry addFuel(Long carId,AddFuelDto addFuelDto);
    public FuelStats getFuelStats(long carId);
}
