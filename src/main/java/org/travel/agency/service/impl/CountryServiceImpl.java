package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.CountryDAO;
import org.travel.agency.entity.Country;
import org.travel.agency.service.CountryService;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryDAO countryDAO;

    @Override
    public List<Country> getAll() {
        List<Country> countries = countryDAO.findAll();
        log.info("Fetched all countries from DB");
        return countries;
    }

    @Override
    public boolean existsByName(String countryName) {
        boolean existsByCountryName =  countryDAO
                                            .findByName(countryName)
                                            .isPresent();
        log.info("Country with a name {} exists - {}", countryDAO, existsByCountryName);
        return existsByCountryName;
    }
}
