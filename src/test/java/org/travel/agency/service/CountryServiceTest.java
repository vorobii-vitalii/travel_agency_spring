package org.travel.agency.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.config.HibernateConfig;
import org.travel.agency.config.WebConfig;
import org.travel.agency.dao.CountryDAO;
import org.travel.agency.entity.Country;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryDAO countryDAO;

    private int countriesCount;

    @Before
    public void init() {
        List<Country> initCountries = initCountries();
        for (Country country : initCountries) {
            countryDAO.save(country);
        }
        countriesCount = initCountries.size();
    }

    @Test
    public void testGetAll() {
        List<Country> countries = countryService.getAll();
        assertEquals(countriesCount, countries.size());
    }

    @Test
    public void testExistsByWrongName() {
        boolean existsByWrongName = countryService.existsByName("Unknown");
        assertFalse(existsByWrongName);
    }
    @Test
    public void testExistsByCorrectName() {
        boolean existsByCorrectName = countryService.existsByName("Ukraine");
        assertTrue(existsByCorrectName);
    }

    private List<Country> initCountries() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(null, "Ukraine"));
        countries.add(new Country(null, "Russia"));
        countries.add(new Country(null, "USA"));
        return countries;
    }

}