package com.hotelbooking.search_service.service;

import com.hotelbooking.common.event.HotelEvent;
import com.hotelbooking.common.event.RoomEvent;
import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.entity.HotelSearchFilter;
import com.hotelbooking.search_service.repository.HotelSearchRepository;
import com.hotelbooking.search_service.repository.HotelSearchRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelIndexService {


    private  final HotelSearchRepository hotelSearchRepository;

    private  final HotelSearchRepositoryImpl hotelCustomSearchRepository;

   public HotelSearchDocument saveOrUpdateHotel(HotelSearchDocument hotelSearchDocument){
       return hotelSearchRepository.save(hotelSearchDocument);

    }
    public List<HotelSearchDocument> searchByCity(String city){
       return hotelSearchRepository.findByCity(city);
    }
    public List<HotelSearchDocument> search(HotelSearchFilter hotelSearchFilter) {
        return hotelCustomSearchRepository.searchByCityNameAndMinRating(hotelSearchFilter);
   }



    public void indexHotel(HotelEvent hotelEvent) {
       log.info("Indexing hotel {} on Elastic search", hotelEvent.getHotelId());
        UUID hotelId = hotelEvent.getHotelId();
        Optional<HotelSearchDocument> existing = hotelSearchRepository.findById(hotelId);

        HotelSearchDocument doc = existing.orElse(new HotelSearchDocument());
        doc.setHotelId(hotelId);
        doc.setName(hotelEvent.getName());
        doc.setCity(hotelEvent.getCity());
        doc.setRating(hotelEvent.getRating());

        hotelSearchRepository.save(doc);
    }
    public void indexRoom(RoomEvent roomEvent) {
        log.info("Indexing room {} on Elastic search", roomEvent.getHotelId());

        UUID hotelId = roomEvent.getHotelId();
        Optional<HotelSearchDocument> existing = hotelSearchRepository.findById(hotelId);

        HotelSearchDocument doc = existing.orElse(new HotelSearchDocument());
        doc.setHotelId(hotelId);

        List<HotelSearchDocument.Room> rooms = doc.getRooms();
        if (rooms == null) rooms = new ArrayList<>();

        rooms.removeIf(r -> r.getRoomId().equals(roomEvent.getRoomId()));

        HotelSearchDocument.Room room = new HotelSearchDocument.Room();
        room.setRoomId(roomEvent.getRoomId());
        room.setType(roomEvent.getType());
        room.setPrice(roomEvent.getPrice());

        rooms.add(room);
        doc.setRooms(rooms);

        hotelSearchRepository.save(doc);
    }


}
