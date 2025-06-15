package com.hotelbooking.search_service;


import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.service.HotelIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class controller {
    private final HotelIndexService hotelIndexService;

    /**
     * Search hotels by city name.
     * Example: GET /api/search?city=Goa
     */
    @GetMapping("/api/search")
    public List<HotelSearchDocument> searchByCity(@RequestParam String city) {
        return hotelIndexService.searchByCity(city);
    }
}
