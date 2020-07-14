package org.travel.agency.service;

import org.travel.agency.dto.BookRoomRequest;
import org.travel.agency.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    List<Order> getAllByUserId(Long userId);
    void addOrder(BookRoomRequest bookRoomRequest, Long customerId);
}
