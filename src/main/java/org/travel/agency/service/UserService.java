package org.travel.agency.service;

import org.travel.agency.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    List<User> getAllByRole(String role);
    User getById(Long id);
    User getByEmail(String email);
    void save(User user);
}
