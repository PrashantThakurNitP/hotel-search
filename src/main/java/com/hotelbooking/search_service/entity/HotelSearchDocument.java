package com.hotelbooking.search_service.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Document(indexName = "search-hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Hotel search result document")
public class HotelSearchDocument{
    @Id
    @Schema(description = "Unique hotel ID", example = "hotel-123")
    private UUID hotelId;

    @Schema(description = "Hotel name", example = "Taj Palace")
    private String name;

    @Schema(description = "Hotel city", example = "Delhi")
    private String city;

    @Schema(description = "Average rating of the hotel (0.0 to 5.0)", example = "4.5")
    private Double rating;

    private List<Room> rooms;

    @Data
    public static class Room {

        private UUID roomId;

        @Schema(description = "Type of the room (e.g., Deluxe, Suite)", example = "Deluxe")
        private String type;

        @Schema(description = "Price per night", example = "3500")
        private BigDecimal price;
    }


}
