package com.hotelbooking.search_service.service;

import com.hotelbooking.common.event.HotelEvent;
import com.hotelbooking.common.event.RoomEvent;
import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.repository.HotelSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelIndexService {

    private final HotelSearchRepository hotelSearchRepository;

   public HotelSearchDocument saveOrUpdateHotel(HotelSearchDocument hotelSearchDocument){
       return hotelSearchRepository.save(hotelSearchDocument);

    }
    public List<HotelSearchDocument> searchByCity(String city){
       return hotelSearchRepository.findByCity(city);
    }


    public void indexHotel(HotelEvent hotelEvent) {
        UUID hotelId = hotelEvent.getHotelId();
        Optional<HotelSearchDocument> existing = hotelSearchRepository.findById(hotelId);

        HotelSearchDocument doc = existing.orElse(new HotelSearchDocument());
        doc.setHotelId(hotelId);
        doc.setName(hotelEvent.getName());
        doc.setCity(hotelEvent.getCity());

        hotelSearchRepository.save(doc);
    }
    public void indexRoom(RoomEvent roomEvent) {

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
