package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.HotelDAO;
import org.travel.agency.entity.Hotel;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.HotelService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;

    @Override
    public List<Hotel> getAll() {
        return hotelDAO.findAll();
    }

    @Override
    public List<Hotel> getAllByCountry(String country) {
        return hotelDAO.findAllByCountry(country);
    }

    @Override
    public List<Hotel> getAllByName(String name) {
        return hotelDAO.findAllByName(name);
    }

    @Override
    public Hotel getById(Long id) {
        return hotelDAO
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelDAO.deleteById(id);
    }

}
