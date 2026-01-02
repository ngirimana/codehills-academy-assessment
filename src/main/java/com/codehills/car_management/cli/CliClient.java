package com.codehills.car_management.cli;

import com.codehills.car_management.backend.dto.FuelStats;
import com.codehills.car_management.backend.models.Car;
import com.codehills.car_management.backend.utils.AppConstants;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CliClient {
private static final DecimalFormat COST_2DP = new DecimalFormat("0.00");
private static final DecimalFormat AVG_1DP  = new DecimalFormat("0.0");
private static final DecimalFormat FUEL_NICE = new DecimalFormat("0.##");

public static void main(String[] args) {
    ApiClient api = new ApiClient(AppConstants.BASE_URL);

    System.out.println(AppConstants.CLI);
    System.out.println(AppConstants.HELP_MESSAGE);

    try (Scanner sc = new Scanner(System.in)) {
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine();
            if (line == null) break;

            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.equalsIgnoreCase(AppConstants.EXIT)) break;
            if (line.equalsIgnoreCase(AppConstants.HELP_COMMAND)) {
                printHelp();
                continue;
            }

            try {
                List<String> tokens = Tokenizer.tokenize(line);
                if (tokens.isEmpty()) continue;

                String command = tokens.get(0);
                String[] arr = tokens.toArray(new String[0]);

                Map<String, String> kv = CliArgs.parseKeyValue(arr, 1);

                switch (command) {
                    case AppConstants.CREATE_CAR -> {
                        String brand = CliArgs.require(kv, AppConstants.BRAND);
                        String model = CliArgs.require(kv, AppConstants.MODEL);
                        int year = CliArgs.requireInt(kv, AppConstants.YEAR);

                        Car car = api.createCar(brand, model, year);
                        System.out.println(AppConstants.CREATE_CAR_SUCCESS + car.getId());
                    }

                    case AppConstants.ADD_FUEL -> {
                        long carId = CliArgs.requireLong(kv, AppConstants.CAR_ID);
                        double liters = CliArgs.requireDouble(kv, AppConstants.LITERS);
                        double price = CliArgs.requireDouble(kv, AppConstants.PRICE);
                        double odometer = CliArgs.requireDouble(kv, AppConstants.ODOMETER);

                        api.addFuel(carId, liters, price, odometer);
                        System.out.println(AppConstants.FUEL_ADDED_SUCCESS);
                    }

                    case AppConstants.FUEL_STATS -> {
                        long carId = CliArgs.requireLong(kv, AppConstants.CAR_ID);
                        FuelStats stats = api.getFuelStats(carId);
                        System.out.println(AppConstants.TOTAL_FUEL + FUEL_NICE.format(stats.totalFuel()) + "L");
                        System.out.println(AppConstants.TOTAL_COST + COST_2DP.format(stats.totalCost()));
                        System.out.println(AppConstants.AVERAGE_CONSUMPTION + AVG_1DP.
                                format(stats.averageConsumptionPer100Km()) + AppConstants.PER_100_KM);
                    }
                    default -> System.out.println(AppConstants.UNKNOWN_COMMAND);
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println(AppConstants.ERROR_PREFIX + e.getMessage());
                System.out.println();
            }
        }
    }
    System.out.println(AppConstants.BYE);
}

private static void printHelp() {
    System.out.println(AppConstants.HELP);
    }
}
