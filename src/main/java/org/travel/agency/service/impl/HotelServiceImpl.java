package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.HotelDAO;
import org.travel.agency.entity.Hotel;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.HotelService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;

    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels = hotelDAO.findAll();
        log.info("Fetched all hotels from DB");
        return hotels;
    }

    @Override
    public List<Hotel> getAllByCountry(String country) {
        List<Hotel> hotelsByCountry = hotelDAO.findAllByCountry(country);
        log.info("Fetched all hotels from DB by country name - {}" ,country);
        return hotelsByCountry;
    }

    @Override
    public List<Hotel> getAllByName(String name) {
        List<Hotel> hotelsByName = hotelDAO.findAllByName(name);
        log.info("Fetched all hotels from DB by name LIKE {}", name);
        return hotelsByName;
    }

    @Override
    public Hotel getById(Long id) {
        Hotel hotelById =  hotelDAO
                                .findById(id)
                                .orElseThrow(NotFoundException::new);
        log.info("Successfully found hotel by id {}" , id);
        return hotelById;
    }

    @Override
    public void save(Hotel hotel) {
        hotelDAO.save(hotel);
        log.info("Saved hotel");
    }

    @Override
    public void deleteById(Long id) {
        hotelDAO.deleteById(id);
        log.info("Hotel with id {} has been permanently deleted", id);
    }

}
