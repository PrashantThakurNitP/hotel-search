package com.hotelbooking.search_service.repository;

import com.hotelbooking.search_service.entity.HotelSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface HotelSearchRepository extends ElasticsearchRepository<HotelSearchDocument, UUID> {
    List<HotelSearchDocument> findByCity(String city);
}
