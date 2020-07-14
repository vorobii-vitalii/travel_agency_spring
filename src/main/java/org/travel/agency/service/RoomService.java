package org.travel.agency.service;

import org.travel.agency.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<Room> getAllByHotelId(Long hotelId);
    List<Room> getAllByHotelIdAndDateBeyond(Long hotelId, LocalDate fromDate, LocalDate tillDate);
    boolean havingOrdersDuringDateBetween(Long roomId, LocalDate dateFrom, LocalDate dateTill);
    Room getById(Long id);
    void save(Room roomToSave);
    Room deleteById(Long id);
}
