package com.codehills.car_management.backend.service.impl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import com.codehills.car_management.backend.service.CarService;
import org.springframework.stereotype.Service;
import com.codehills.car_management.backend.dto.AddFuelDto;
import com.codehills.car_management.backend.dto.CreateCarDto;
import com.codehills.car_management.backend.dto.FuelStats;
import com.codehills.car_management.backend.exception.CarNotFoundException;
import com.codehills.car_management.backend.models.Car;
import com.codehills.car_management.backend.models.FuelEntry;
import com.codehills.car_management.backend.utils.OdometerLevel;
import com.codehills.car_management.backend.utils.Odometer;

@Service
public class CarServiceImpl implements CarService {
    private final AtomicLong carIdGen = new AtomicLong(1);
    private final Map<Long, Car> cars = new ConcurrentHashMap<>();
    private final Map<Long, List<FuelEntry>> fuelByCar = new ConcurrentHashMap<>();

    @Override
    public Car addCar(CreateCarDto carDto) {
        long id = carIdGen.getAndIncrement();
        Car car = new Car(id, carDto.brand(), carDto.model(), carDto.year());
        cars.put(id, car);
        fuelByCar.put(id, Collections.synchronizedList(new ArrayList<>()));
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        return cars.values().stream().sorted(Comparator.comparingLong(Car::getId))
                .toList();
    }

    @Override
    public Optional<Car> getCarById(Long carId) {
        Car car = cars.get(carId);
        return Optional.ofNullable(car);
    }

    @Override
    public FuelEntry addFuel(Long carId, AddFuelDto addFuelDto) {
        Optional<Car> car = getCarById(carId);
        if (car.isEmpty()) {
            throw new CarNotFoundException("car", "id",carId);
        }
        double totalCost = addFuelDto.liters() * addFuelDto.pricePerLiter();
        FuelEntry fuelEntry = new FuelEntry(
                addFuelDto.liters(),
                totalCost,
                addFuelDto.odometer());
        fuelByCar.get(carId).add(fuelEntry);
        return fuelEntry;
    }

    @Override
    public FuelStats getFuelStats(long carId) {
        Optional<Car> car = getCarById(carId);
        if (car.isEmpty()) {
            throw new CarNotFoundException("car", "id",carId);
        }
        List<FuelEntry> fuelEntries = fuelByCar.get(carId);
        if (fuelEntries.isEmpty()) {
            return new FuelStats(0, 0, 0);
        } else {
            double totalLiters = fuelEntries.stream()
                    .mapToDouble(FuelEntry::getLiters)
                    .sum();
            double totalCost = fuelEntries.stream().mapToDouble(
                            FuelEntry::getPrice)
                    .sum();
            double max= Odometer.getOdometer(fuelEntries, OdometerLevel.MAX);
            double min= Odometer.getOdometer(fuelEntries, OdometerLevel.MIN);
            double totalDistance = max - min;
            double averageOdometer = (totalLiters / totalDistance)*100;
            return new FuelStats(totalLiters, totalCost, averageOdometer);
        }
    }
}
