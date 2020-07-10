package org.travel.agency.service;

import org.travel.agency.entity.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAll();
    List<Hotel> getAllByCountry(String country);
    List<Hotel> getAllByName(String name);
    Hotel getById(Long id);
    void save(Hotel hotel);
    void deleteById(Long id);
}
