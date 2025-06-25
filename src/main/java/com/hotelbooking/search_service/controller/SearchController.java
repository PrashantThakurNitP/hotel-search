package com.hotelbooking.search_service.controller;


import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.entity.HotelSearchFilter;
import com.hotelbooking.search_service.service.HotelIndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Search Controller", description = "APIs for searching hotels using city or filter criteria")
@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final HotelIndexService hotelIndexService;

    @Operation(summary = "Search hotels by city", description = "Returns a list of hotels available in a given city.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid city parameter")
    })
    @GetMapping()
    public List<HotelSearchDocument> searchHotelByCity(@RequestParam String city) {
        return hotelIndexService.searchByCity(city);
    }

    @Operation(summary = "Search hotels using filters", description = "Returns hotels matching given filter criteria such as city, price range, rating, etc.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid search filter")
    })
    @PostMapping()
    public ResponseEntity<List<HotelSearchDocument>> searchHotels(@Valid @RequestBody HotelSearchFilter filter) {
        return ResponseEntity.ok(hotelIndexService.search(filter));
    }
}
