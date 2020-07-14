package org.travel.agency.dao;

import org.travel.agency.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDAO {
    List<Country> findAll();
    Optional<Country> findByName(String countryName);
    void save(Country countryToSave);
}
