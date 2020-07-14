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
import org.travel.agency.entity.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class HotelDAOTest {

    @Autowired
    private HotelDAO hotelDAO;

    private Long lastId;

    private int size;

    @Before
    public void init() {
        List<Hotel> initHotels = initHotels();
        for (Hotel hotel : initHotels) {
            hotelDAO.save(hotel);
        }
        size = initHotels.size();
        lastId = initHotels.get(size - 1).getId();
    }

    @Test
    public void testAllHotelsSizeMatch() {
        List<Hotel> hotels = hotelDAO.findAll();
        assertEquals(hotels.size(), size);
    }

    @Test
    public void testAllHotelsByCountry() {
        final String country = "Ukraine";
        List<Hotel> hotelsByCountry = hotelDAO.findAllByCountry(country);
        for (Hotel hotel : hotelsByCountry) {
            assertEquals(hotel.getCountry(), country);
        }
    }

    @Test
    public void testFindById() {
        final Long id = lastId - 1;
        Optional<Hotel> foundHotel = hotelDAO.findById(id);
        assertTrue(foundHotel.isPresent());
        Hotel hotel = foundHotel.get();
        assertEquals(id, hotel.getId());
    }

    @Test
    public void testSaveNew() {
        Hotel hotel = Hotel.builder().name("new hotel").country("Russia").build();
        hotelDAO.save(hotel);
        Long expectedId = lastId + 1;
        assertEquals(expectedId, hotel.getId());
        assertEquals("Russia", hotel.getCountry());
        assertEquals("new hotel", hotel.getName());
        size++;
        lastId++;
    }

    private List<Hotel> initHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(Hotel.builder().name("Nadia").country("Ukraine").build());
        hotels.add(Hotel.builder().name("Nash Kray").country("Ukraine").build());
        hotels.add(Hotel.builder().name("Some hotel").country("USA").build());
        return hotels;
    }
}