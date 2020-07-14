package org.travel.agency.dao.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.travel.agency.dao.UserDAO;
import org.travel.agency.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> findUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u where u.id=:id", User.class)
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .uniqueResultOptional();
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery("select u from User u", User.class)
                .list();
    }

    @Override
    public List<User> findAllByRole(String role) {
        Session session = sessionFactory.getCurrentSession();
        return session
                .createQuery(
                    "select u from User u where " +
                       "exists (select r from u.roles r where r.name=:role)", User.class)
                .setParameter("role", role)
                .list();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

}
