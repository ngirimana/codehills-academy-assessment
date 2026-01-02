package com.codehills.car_management.backend.utils;

public class AppConstants {
    public static final String HELP ="""
Commands examples:
  create-car --brand Toyota --model Corolla --year 2018
  add-fuel --carId 1 --liters 40 --price 52.5 --odometer 45000
  fuel-stats --carId 1

Notes:
  - You can use quotes for spaces:
      create-car --brand "Land Rover" --model "Range Rover" --year 2020
""";
    public static final String UNKNOWN_COMMAND = "Unknown command. Type 'help'.";
    public static final String CREATE_CAR_SUCCESS = "Car created with ID: ";
    public static final String FUEL_ADDED_SUCCESS = "Fuel entry added successfully.";
    public static final String HELP_MESSAGE ="Type your commands, or 'help', or 'exit'.\n";
    public static final String CLI ="Car Management CLI Client";
    public static final String BASE_URL ="http://localhost:8080/api/cars";
    public static final String TOTAL_FUEL ="Total fuel: ";
    public static final String TOTAL_COST ="Total cost: ";
    public static final String AVERAGE_CONSUMPTION ="Average consumption: ";
    public static final String CREATE_CAR = "create-car";
    public static final String ADD_FUEL = "add-fuel";
    public static final String FUEL_STATS = "fuel-stats";
    public static final String CAR_ID = "carId";
    public static final String LITERS = "liters";
    public static final String PRICE = "price";
    public static final String ODOMETER = "odometer";
    public static final String EXIT = "exit";
    public static final String PER_100_KM = " L/100km";
    public static final String HELP_COMMAND = "help";
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String YEAR = "year";
    public static final String BYE ="Bye!";
    public static final String ERROR_PREFIX = "Error: ";
    public static final String UNCLOSED_QUOTE = "Unclosed quote in command";
    public static final String MISSING_VALUE= "Missing value for --";
    public static final String MISSING = "Missing --";
    public static final String INVALID = "Invalid --";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String FUEL_ROUTE = "/fuel";
    public static final String FUEL_STATS_ROUTE = "/fuel/stats";
    public static final String MISSING_CAR_ID = "{\"error\":\"Missing carId\"}";
    public static final String INVALID_CAR_ID = "{\"error\":\"Invalid carId\"}";
    public static final String CAR_NOT_FOUND = "{\"error\":\"Car not found\"}";
    public static final String FUEL_STATS_FORMAT =
            "{\"totalFuel\":%.2f,\"totalCost\":%.2f,\"averageConsumptionPer100Km\":%.2f}";
    public static final String RESOURCE_NOT_FOUND = "%s not found with %s : '%s'";
    public static final String SERVLET_ROUTE = "/servlet/fuel-stats";
    public static final String MAIN_SUB_ROUTE = "/api/cars";
    public static final String ADD_FUEL_API_ROUTE = "/{id}/fuel";
    public static final String FUEL_STATS_API_ROUTE = "/{id}/fuel/stats";

}
