package org.travel.agency.dao;

import org.travel.agency.entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> findAll();
    List<Order> findAllByUserId(Long userId);
    void save(Order orderToSave);
}
