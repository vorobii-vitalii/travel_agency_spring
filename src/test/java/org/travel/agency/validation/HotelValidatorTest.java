package org.travel.agency.validation;

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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.travel.agency.config.HibernateConfig;
import org.travel.agency.config.WebConfig;
import org.travel.agency.dao.CountryDAO;
import org.travel.agency.entity.Country;
import org.travel.agency.entity.Hotel;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class HotelValidatorTest {

    @Autowired
    private CountryDAO countryDAO;

    @Autowired
    private HotelValidator hotelValidator;

    @Before
    public void init() {
        countryDAO.save(new Country(null, "Ukraine"));
    }

    @Test
    public void testHotelEntitySupports() {
        boolean result = hotelValidator.supports(Hotel.class);
        assertTrue(result);
    }

    @Test
    public void testHotelValidatorUnknownCountry() {
        Hotel hotel = Hotel.builder()
                                .name("Correct name")
                                .country("Wrong country")
                                .build();
        Errors errors = new BeanPropertyBindingResult(hotel, "hotel");
        hotelValidator.validate(hotel, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("country"));
    }

    @Test
    public void testHotelValidatorCorrectCredentials() {
        Hotel hotel = Hotel.builder()
                .name("Correct name")
                .country("Ukraine")
                .build();
        Errors errors = new BeanPropertyBindingResult(hotel, "hotel");
        hotelValidator.validate(hotel, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testHotelValidatorEmptyName() {
        Hotel hotel = Hotel.builder()
                .name("")
                .country("Ukraine")
                .build();
        Errors errors = new BeanPropertyBindingResult(hotel, "hotel");
        hotelValidator.validate(hotel, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("name"));
    }

}