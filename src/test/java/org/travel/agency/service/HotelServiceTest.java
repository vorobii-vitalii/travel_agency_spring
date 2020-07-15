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
import org.travel.agency.dao.HotelDAO;
import org.travel.agency.entity.Hotel;
import org.travel.agency.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class HotelServiceTest {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private HotelService hotelService;

    private Long correctId;

    @Before
    public void init() {
        Hotel hotel = new Hotel(null, "Nadia", "Some hotel", "Ukraine", null);
        hotelDAO.save(hotel);
        correctId = hotel.getId();
    }

    @Test
    public void testGetByCorrectId() {
        Hotel hotel = hotelService.getById(correctId);
        assertEquals(correctId, hotel.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByWrongIdThrowsException() {
        hotelService.getById(correctId + 13L);
    }

}