package org.travel.agency.service;

import org.travel.agency.entity.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAllByHotelId(Long hotelId);
    Room getById(Long id);
    void save(Room roomToSave);
    Room deleteById(Long id);
}
