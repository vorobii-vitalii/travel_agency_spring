package org.travel.agency.dao;

import org.travel.agency.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();
    List<User> findAllByRole(String role);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    void save(User user);
}
