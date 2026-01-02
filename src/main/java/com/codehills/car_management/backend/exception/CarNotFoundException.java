package com.codehills.car_management.backend.exception;

import com.codehills.car_management.backend.utils.AppConstants;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException {
    public String resourceName;
    public String fieldName;
    public Long fieldValue;

    public CarNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format(AppConstants.RESOURCE_NOT_FOUND, resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
