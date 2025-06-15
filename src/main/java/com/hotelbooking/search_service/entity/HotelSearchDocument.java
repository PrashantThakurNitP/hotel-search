package com.hotelbooking.search_service.entity;


import com.hotelbooking.common.entity.HotelDocument;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;


@AllArgsConstructor
@Document(indexName = "hotels")
public class HotelSearchDocument extends HotelDocument {
    @Id
    public UUID getHotelId() {
        return super.getHotelId();
    }
}
