package org.travel.agency.dao;

import org.travel.agency.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomDAO {
    List<Room> findAllByHotelId(Long hotelId);
    Optional<Room> findById(Long id);
    List<Room> findAllByHotelIdAndDateBeyond(Long hotelId, LocalDate dateFrom, LocalDate dateTill);
    boolean havingOrdersDuringDateBetween(Long roomId, LocalDate dateFrom, LocalDate dateTill);
    void save(Room room);
    void deleteById(Long id);
}
