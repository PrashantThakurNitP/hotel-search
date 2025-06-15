package com.hotelbooking.search_service.mapper;



import com.hotelbooking.search_service.entity.HotelSearchDocument;

import java.util.List;
import java.util.stream.Collectors;

public class HotelDocumentMapper {

    public static HotelSearchDocument toSearchDocument(
            com.hotelbooking.common.entity.HotelDocument hotel) {

        HotelSearchDocument esHotel =
                new HotelSearchDocument();

        esHotel.setHotelId(hotel.getHotelId());
        esHotel.setName(hotel.getName());
        esHotel.setCity(hotel.getCity());

        List<HotelSearchDocument.Room> roomList = hotel.getRooms().stream().map(r -> {
            HotelSearchDocument.Room room =
                    new HotelSearchDocument.Room();
            room.setRoomId(r.getRoomId());
            room.setType(r.getType());
            room.setPrice(r.getPrice());

            return room;
        }).collect(Collectors.toList());

        esHotel.setRooms(roomList);

        return esHotel;
    }
}
