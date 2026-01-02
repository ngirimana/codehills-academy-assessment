package com.codehills.car_management.backend.controller;

import com.codehills.car_management.backend.dto.AddFuelDto;
import com.codehills.car_management.backend.dto.CreateCarDto;
import com.codehills.car_management.backend.models.Car;
import com.codehills.car_management.backend.models.FuelEntry;
import com.codehills.car_management.backend.service.CarService;
import com.codehills.car_management.backend.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.MAIN_SUB_ROUTE)
public class CarController {
    final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> createCar(@Valid @RequestBody CreateCarDto createCarDto) {
        return new ResponseEntity<>(carService.addCar(createCarDto), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(),HttpStatus.OK);
    }

    @PostMapping(AppConstants.ADD_FUEL_API_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FuelEntry> addFuel(@PathVariable("id") Long carId, @Valid @RequestBody AddFuelDto addFuelDto) {
        return new ResponseEntity<>(carService.addFuel(carId, addFuelDto), HttpStatus.CREATED);
    }

    @GetMapping(AppConstants.FUEL_STATS_API_ROUTE)
    @ResponseStatus(HttpStatus.OK)
    public Object getFuelStats(@PathVariable("id") Long carId) {
        return carService.getFuelStats(carId);
    }
}
