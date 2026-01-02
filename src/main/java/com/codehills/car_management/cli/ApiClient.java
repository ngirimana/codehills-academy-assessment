package com.codehills.car_management.cli;

import com.codehills.car_management.backend.dto.AddFuelDto;
import com.codehills.car_management.backend.dto.CreateCarDto;
import com.codehills.car_management.backend.dto.FuelStats;
import com.codehills.car_management.backend.models.Car;
import com.codehills.car_management.backend.utils.AppConstants;
import tools.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {

    private final String baseUrl;
    private final HttpClient http;
    private final ObjectMapper mapper = new ObjectMapper();

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.
                substring(0, baseUrl.length() - 1) : baseUrl;

        this.http = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public Car createCar(String brand, String model, int year) throws Exception {
        String body = mapper.writeValueAsString(new CreateCarDto(brand, model, year));

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header(AppConstants.CONTENT_TYPE, AppConstants.APPLICATION_JSON)

                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        ensure2xx(res);
        return mapper.readValue(res.body(), Car.class);
    }

    public void addFuel(long carId, double liters, double price, double odometer) throws Exception {
        String body = mapper.writeValueAsString(new AddFuelDto(liters, price, odometer));

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/" + carId + AppConstants.FUEL_ROUTE))
                .header(AppConstants.CONTENT_TYPE, AppConstants.APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        ensure2xx(res);
    }

    public FuelStats getFuelStats(long carId) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl +"/"+ carId + AppConstants.FUEL_STATS_ROUTE))
                .GET()
                .build();

        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        ensure2xx(res);
        return mapper.readValue(res.body(), FuelStats.class);
    }

    private static void ensure2xx(HttpResponse<String> res) {
        int code = res.statusCode();
        if (code < 200 || code >= 300) {
            throw new RuntimeException("HTTP " + code + " - " + (res.body() == null ? "" : res.body()));
        }
    }
}