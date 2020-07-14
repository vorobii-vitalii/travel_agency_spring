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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class OrderDAOTest {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private RoomDAO roomDAO;

    private User customer;

    private Room room;

    private int size;

    private Long lastId;

    @Before
    public void init() {
        initializeCustomer();
        initializeRoom();
        List<Order> orders = getInitOrders();
        size = orders.size();
        for (Order order : orders) {
            orderDAO.save(order);
            lastId = order.getId();
        }
    }

    @Test
    public void testMatchAllOrdersCount() {
        List<Order> orders = orderDAO.findAll();
        assertEquals(size, orders.size());
    }

    @Test
    public void testMatchAllOrdersByCorrectCustomerId() {
        final Long customerId = customer.getId();
        List<Order> customersOrders = orderDAO.findAllByUserId(customerId);
        assertEquals(size, customersOrders.size());
    }

    @Test
    public void testMatchAllOrdersByWrongCustomerId() {
        List<Order> customersOrders = orderDAO.findAllByUserId(-1L);
        assertEquals(0, customersOrders.size());
    }

    @Test
    public void testSave() {
        Order order = new Order(null, LocalDate.of(2000, 5, 1), LocalDate.of(2000, 5, 3), customer, room );
        orderDAO.save(order);
        Long expectedId = lastId + 1;
        assertEquals(expectedId, order.getId());
        size++;
        lastId++;
    }

    private List<Order> getInitOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(null, LocalDate.of(2000, 6, 1), LocalDate.of(2000, 6, 3), customer, room ));
        orders.add(new Order(null, LocalDate.of(2000, 6, 5), LocalDate.of(2000, 6, 7), customer, room ));
        return orders;
    }

    private void initializeRoom() {
        Hotel hotel = Hotel.builder().name("Nadia").country("Ukraine").build();
        hotelDAO.save(hotel);
        room = new Room(null, 24L, hotel.getId(), hotel, null);
        roomDAO.save(room);
    }

    private void initializeCustomer() {
        User customer = new User(null, "george@gmail.com", "George Jonathan", "123" , null, null);
        userDAO.save(customer);
        this.customer = customer;
    }

}