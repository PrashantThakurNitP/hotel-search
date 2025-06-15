package com.hotelbooking.search_service.kafka;


import com.hotelbooking.common.event.HotelEvent;
import com.hotelbooking.common.event.RoomEvent;
import com.hotelbooking.search_service.service.HotelIndexService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SearchKafkaListener {

    private final HotelIndexService hotelIndexService;

    public SearchKafkaListener(HotelIndexService hotelIndexService) {
        this.hotelIndexService = hotelIndexService;
    }

    @KafkaListener(
            topics = "hotel-events",
            groupId = "search-service-group",
            containerFactory = "hotelKafkaListenerContainerFactory"
    )
    public void listen(HotelEvent hotelEvent) {
        hotelIndexService.indexHotel(hotelEvent);
    }

    @KafkaListener(
            topics = "room-events",
            groupId = "search-service-group",
            containerFactory = "roomKafkaListenerContainerFactory"
    )
    public void listen(RoomEvent roomEvent) {
        hotelIndexService.indexRoom(roomEvent);
    }
}
