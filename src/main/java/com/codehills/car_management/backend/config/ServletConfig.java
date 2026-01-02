package com.codehills.car_management.backend.config;

import com.codehills.car_management.backend.service.CarService;
import com.codehills.car_management.backend.servlet.FuelStatsServlet;
import com.codehills.car_management.backend.utils.AppConstants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet(CarService carService) {
        FuelStatsServlet servlet = new FuelStatsServlet(carService);
        return new ServletRegistrationBean<>(servlet, AppConstants.SERVLET_ROUTE);
    }
}
