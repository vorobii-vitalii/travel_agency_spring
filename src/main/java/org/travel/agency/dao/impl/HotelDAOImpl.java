package org.travel.agency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.travel.agency.dao.HotelDAO;
import org.travel.agency.entity.Hotel;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HotelDAOImpl implements HotelDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Hotel> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select h from Hotel h", Hotel.class)
                .list();
    }

    @Override
    public List<Hotel> findAllByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select h from Hotel h where h.country = :country", Hotel.class)
                .setParameter("country", country)
                .list();
    }

    @Override
    public List<Hotel> findAllByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select h from Hotel h where h.name like :name ", Hotel.class)
                .setParameter("name", '%' + name + '%')
                .list();
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                    .createQuery("select h from Hotel h where h.id = :id ", Hotel.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
    }

    @Override
    public void save(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hotel);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Hotel h where h.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
