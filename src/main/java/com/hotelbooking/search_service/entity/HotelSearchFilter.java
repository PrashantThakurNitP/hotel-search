package com.hotelbooking.search_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(description = "Filter criteria for hotel search")
public class HotelSearchFilter {

    @Size(min = 2, max = 50, message = "City name must be between 2 and 50 characters")
    @Schema(description = "City to search hotels in", example = "Delhi")
    private String city;

    @Size(min = 2, max = 50, message = "Hotel name must be between 2 and 50 characters")
    @Schema(description = "Name of the hotel", example = "Taj Palace")
    private String name;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating cannot exceed 5")
    @Schema(description = "Minimum rating required", example = "3.5")
    private Double minRating;

}
