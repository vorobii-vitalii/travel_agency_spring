package org.travel.agency.dao;

import org.travel.agency.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDAO {
    List<Room> findAllByHotelId(Long hotelId);
    Optional<Room> findById(Long id);
    void save(Room room);
    void deleteById(Long id);
}
