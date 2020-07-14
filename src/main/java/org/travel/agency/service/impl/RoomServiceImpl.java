package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.RoomDAO;
import org.travel.agency.entity.Room;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.HotelService;
import org.travel.agency.service.RoomService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO;
    private final HotelService hotelService;

    @Override
    public List<Room> getAllByHotelId(Long hotelId) {
        List<Room> roomsByHotelId = roomDAO.findAllByHotelId(hotelId);
        log.info("Fetched all rooms by hotel id {}", hotelId);
        return roomsByHotelId;
    }

    @Override
    public List<Room> getAllByHotelIdAndDateBeyond(Long hotelId, LocalDate fromDate, LocalDate tillDate) {
        List<Room> roomsByHotelIdAndDateBeyond = roomDAO.findAllByHotelIdAndDateBetween(hotelId, fromDate, tillDate);
        log.info("Fetched available rooms from {} till {} in hotel by id {}", fromDate, tillDate, hotelId);
        return roomsByHotelIdAndDateBeyond;
    }

    @Override
    public boolean havingOrdersDuringDateBetween(Long roomId, LocalDate dateFrom, LocalDate dateTill) {
        boolean havingOrdersDuringDateBetween = roomDAO.havingOrdersDuringDateBetween(roomId, dateFrom, dateTill);
        log.info("Checked whether room with id {} is available from {} to {}", roomId, dateFrom, dateTill);
        return havingOrdersDuringDateBetween;
    }

    @Override
    public Room getById(Long id) {
        Room roomById = roomDAO
                            .findById(id)
                            .orElseThrow(NotFoundException::new);
        log.info("Gotten room by id {}", id);
        return roomById;
    }

    @Override
    public void save(Room roomToSave) {
        roomToSave.setHotel(hotelService.getById(roomToSave.getHotelId()));
        roomDAO.save(roomToSave);
        log.info("Saved room");
    }

    @Override
    public Room deleteById(Long id) {
        Room roomToDelete = getById(id);
        roomDAO.deleteById(id);
        log.info("Deleted room with id {}", id);
        return roomToDelete;
    }

}
