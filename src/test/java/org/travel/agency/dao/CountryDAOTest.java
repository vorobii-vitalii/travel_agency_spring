package org.travel.agency.dao;

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
import org.travel.agency.entity.Country;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class CountryDAOTest {

    @Autowired
    private CountryDAO countryDAO;

    private Long lastIndex;
    private int initSize;

    @Before
    public void init() {
        List<Country> initData = List.of (
                countryByName("Ukraine"),
                countryByName("Russia")
        );
        for (Country country : initData) {
            countryDAO.save(country);
            lastIndex = country.getId();
        }
        initSize = initData.size();
    }

    @Test
    public void testCountryIsPresent() {
        assertTrue(countryDAO.findByName("Ukraine").isPresent());
    }

    @Test
    public void findAll() {
        List<Country> countries = countryDAO.findAll();
        assertEquals(initSize, countries.size());
    }

    @Test
    public void shouldReturnNextIndex() {
        Country country = new Country(null, "Ukraine");
        countryDAO.save(country);
        Long expectedId = lastIndex + 1;
        assertEquals(expectedId, country.getId());
    }

    private Country countryByName(String name) {
        return new Country(null, name);
    }

}