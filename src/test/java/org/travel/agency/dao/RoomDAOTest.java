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
import org.travel.agency.entity.Order;
import org.travel.agency.entity.Room;
import org.travel.agency.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class RoomDAOTest {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private OrderDAO orderDAO;

    private Long hotelId;

    private Long roomId;

    @Before
    public void init() {
        Hotel hotel = getHotel();
        User customer = getCustomer();
        Room room = initRoom(hotel);
        initOrders(customer, room);
        hotelId = hotel.getId();
        roomId = room.getId();
    }

    @Test
    public void testFindByCorrectId() {
        Optional<Room> roomOptional = roomDAO.findById(roomId);
        assertTrue(roomOptional.isPresent());
        Room room = roomOptional.get();
        assertEquals(roomId, room.getId());
    }

    @Test
    public void testFindByWrongId() {
        Optional<Room> roomOptional = roomDAO.findById(-1L);
        assertFalse(roomOptional.isPresent());
    }

    @Test
    public void testFindAllByCorrectHotelId() {
        List<Room> rooms = roomDAO.findAllByHotelId(hotelId);
        assertFalse(rooms.isEmpty());
    }

    @Test
    public void testFindAllByWrongHotelId() {
        List<Room> rooms = roomDAO.findAllByHotelId(-1L);
        assertTrue(rooms.isEmpty());
    }

    @Test
    public void testFindAllByHotelIdAndDateBeyondIntersects() {
        List<Room> rooms = roomDAO.findAllByHotelIdAndDateBeyond(hotelId, LocalDate.of(2000, 6, 2), LocalDate.of(2000, 6, 4));
        assertTrue(rooms.isEmpty());
    }

    @Test
    public void testFindAllByHotelIdAndDateBeyond() {
        List<Room> rooms = roomDAO.findAllByHotelIdAndDateBeyond(hotelId, LocalDate.of(2000, 6, 8), LocalDate.of(2000, 6, 10));
        assertFalse(rooms.isEmpty());
    }

    @Test
    public void testHavingOrdersDuringDateBetweenIntersects() {
        boolean result = roomDAO.havingOrdersDuringDateBetween(roomId, LocalDate.of(2000, 6, 2), LocalDate.of(2000, 6, 5));
        assertTrue(result);
    }

    @Test
    public void testHavingOrdersDuringDateBetween() {
        boolean result = roomDAO.havingOrdersDuringDateBetween(roomId, LocalDate.of(2000, 6, 9), LocalDate.of(2000, 6, 11));
        assertFalse(result);
    }

    private void initOrders(User customer, Room room) {
        orderDAO.save(new Order(null, LocalDate.of(2000, 6, 1), LocalDate.of(2000, 6, 7), customer, room ));
    }

    private Room initRoom(Hotel hotel) {
        Room room = new Room(null, 24L, hotel.getId(), hotel, null);
        roomDAO.save(room);
        return room;
    }

    private User getCustomer() {
        User customer = new User(null, "george@gmail.com", "George Jonathan", "123" , null, null);
        userDAO.save(customer);
        return customer;
    }

    private Hotel getHotel() {
        Hotel hotel = Hotel.builder().name("Nadia").country("Ukraine").build();
        hotelDAO.save(hotel);
        return hotel;
    }

}
