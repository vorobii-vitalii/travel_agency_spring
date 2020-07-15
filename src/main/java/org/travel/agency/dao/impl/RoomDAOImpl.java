package org.travel.agency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.travel.agency.dao.RoomDAO;
import org.travel.agency.entity.Room;

import java.time.LocalDate;
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
                .createQuery("select r from Room r where r.hotelId=:hotelId", Room.class)
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
    public List<Room> findAllByHotelIdAndDateBeyond(Long hotelId, LocalDate dateFrom, LocalDate dateTill) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery(
                "select r from Room r " +
                    "where r.hotelId=:hotelId and " +
                    "not exists (select o from Order o where o.room = r " +
                        "and ( o.dateFrom <= :dateTill and o.dateTill > :dateFrom  )" +
                    ")", Room.class)
                .setParameter("hotelId", hotelId)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTill", dateTill)
                .list();
    }

    public boolean havingOrdersDuringDateBetween(Long roomId, LocalDate dateFrom, LocalDate dateTill) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select r from Room r where r.id = :roomId " +
                        "and exists(select o from Order o where o.room.id = r.id " +
                        "and (o.dateFrom <= :dateTill and o.dateTill > :dateFrom )" +
                        ") ")
                .setParameter("roomId", roomId)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTill", dateTill)
                .uniqueResult() != null;
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
