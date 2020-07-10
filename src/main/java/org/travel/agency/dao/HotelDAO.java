package org.travel.agency.dao;

import org.travel.agency.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelDAO {
    List<Hotel> findAll();
    List<Hotel> findAllByCountry(String country);
    List<Hotel> findAllByName(String name);
    Optional<Hotel> findById(Long id);
    void save(Hotel hotel);
    void deleteById(Long id);
}
