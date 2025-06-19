package com.hotelbooking.search_service.entity;


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
public class HotelSearchDocument{
    @Id
    private UUID hotelId;

    private String name;
    private String city;
    private Double rating;
    private List<Room> rooms;

    @Data
    public static class Room {
        private UUID roomId;
        private String type;
        private BigDecimal price;
    }


}
