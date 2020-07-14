package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.OrderDAO;
import org.travel.agency.dto.BookRoomRequest;
import org.travel.agency.entity.Order;
import org.travel.agency.mapper.Mapper;
import org.travel.agency.service.OrderService;
import org.travel.agency.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;
    private final Mapper<BookRoomRequest, Order> bookRoomRequestOrderMapper;
    private final UserService userService;

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderDAO.findAll();
        log.info("Fetched all orders");
        return orders;
    }

    @Override
    public List<Order> getAllByUserId(Long userId) {
        List<Order> usersOrders = orderDAO.findAllByUserId(userId);
        log.info("Fetched all orders of user by id {}", userId);
        return usersOrders;
    }

    @Override
    public void addOrder(BookRoomRequest bookRoomRequest, Long customerId) {
        Order order = bookRoomRequestOrderMapper.to(bookRoomRequest);
        order.setCustomer(userService.getById(customerId));
        orderDAO.save(order);
        log.info("Customer with id {} successfully reserved room {}", customerId, bookRoomRequest);
    }
}
