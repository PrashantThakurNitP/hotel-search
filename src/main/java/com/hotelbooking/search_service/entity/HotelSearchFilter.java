package com.hotelbooking.search_service.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class HotelSearchFilter {

    @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    private String city;

    @Size(min = 2, max = 50, message = "Hotel name must be between 2 and 50 characters")
    private String name;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Double minRating;

}
