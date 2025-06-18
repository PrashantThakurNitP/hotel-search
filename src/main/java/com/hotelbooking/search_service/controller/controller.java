package com.hotelbooking.search_service.controller;


import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.entity.HotelSearchFilter;
import com.hotelbooking.search_service.service.HotelIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class controller {
    private final HotelIndexService hotelIndexService;

    @GetMapping()
    public List<HotelSearchDocument> searchHotelByCity(@RequestParam String city) {
        return hotelIndexService.searchByCity(city);
    }
    @PostMapping()
    public ResponseEntity<List<HotelSearchDocument>> searchHotels(@RequestBody HotelSearchFilter filter) {
        return ResponseEntity.ok(hotelIndexService.search(filter));
    }
}
