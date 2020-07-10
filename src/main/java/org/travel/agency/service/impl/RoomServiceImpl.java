package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.RoomDAO;
import org.travel.agency.entity.Room;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.RoomService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO;

    @Override
    public List<Room> getAllByHotelId(Long hotelId) {
        return roomDAO.findAllByHotelId(hotelId);
    }

    @Override
    public Room getById(Long id) {
        return roomDAO
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Room roomToSave) {
        roomDAO.save(roomToSave);
    }

    @Override
    public Room deleteById(Long id) {
        Room roomToDelete = getById(id);
        roomDAO.deleteById(id);
        return roomToDelete;
    }

}
