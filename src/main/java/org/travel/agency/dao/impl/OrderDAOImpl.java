package org.travel.agency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.travel.agency.dao.OrderDAO;
import org.travel.agency.entity.Order;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderDAOImpl implements OrderDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o", Order.class)
                .list();
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select o from Order o where o.customer.id = :userId", Order.class)
                .setParameter("userId", userId)
                .list();
    }

    @Override
    public void save(Order orderToSave) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderToSave);
    }
}
