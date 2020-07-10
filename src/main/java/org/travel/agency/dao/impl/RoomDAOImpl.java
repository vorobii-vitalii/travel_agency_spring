package org.travel.agency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.travel.agency.dao.RoomDAO;
import org.travel.agency.entity.Room;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoomDAOImpl implements RoomDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Room> findAllByHotelId(Long hotelId) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select r from Room r inner join Hotel h on h.id = :hotelId", Room.class)
                .setParameter("hotelId", hotelId )
                .list();
    }

    @Override
    public Optional<Room> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select r from Room r where r.id=:id", Room.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public void save(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(room);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Room r where r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
