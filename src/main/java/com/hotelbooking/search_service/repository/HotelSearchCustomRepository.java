package com.hotelbooking.search_service.repository;

import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.entity.HotelSearchFilter;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HotelSearchCustomRepository {
    List<HotelSearchDocument> searchByCityNameAndMinRating(HotelSearchFilter hotelSearchFilter);
}
