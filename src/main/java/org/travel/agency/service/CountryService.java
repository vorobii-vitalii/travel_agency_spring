package org.travel.agency.service;

import org.travel.agency.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAll();
    boolean existsByName(String countryName);
}
