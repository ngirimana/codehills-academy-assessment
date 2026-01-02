package com.codehills.car_management.backend.servlet;

import com.codehills.car_management.backend.dto.FuelStats;
import com.codehills.car_management.backend.exception.CarNotFoundException;
import com.codehills.car_management.backend.service.CarService;
import com.codehills.car_management.backend.utils.AppConstants;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FuelStatsServlet extends HttpServlet {
    private final CarService carService;

    public FuelStatsServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(AppConstants.APPLICATION_JSON);

        String carIdParam = req.getParameter(AppConstants.CAR_ID);
        if (carIdParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(AppConstants.MISSING_CAR_ID);
            return;
        }
        long carId;
        try {
            carId = Long.parseLong(carIdParam);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(AppConstants.INVALID_CAR_ID);
            return;
        }
        try {
            FuelStats stats = carService.getFuelStats(carId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(toJson(stats));
        } catch (CarNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write(AppConstants.CAR_NOT_FOUND);
        }
    }

    private String toJson(FuelStats s) {
        return String.format(
                AppConstants.FUEL_STATS_FORMAT,
                s.totalFuel(), s.totalCost(), s.averageConsumptionPer100Km()
        );
    }
}
