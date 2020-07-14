package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.UserDAO;
import org.travel.agency.entity.User;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> getAllByRole(String role) {
        List<User> usersByRole = userDAO.findAllByRole(role);
        log.info("Fetched all users by role {}", role);
        return usersByRole;
    }

    @Override
    public User getById(Long id) {
        User userById = userDAO
                            .findUserById(id)
                            .orElseThrow(NotFoundException::new);
        log.info("Gotten user by id {}", id);
        return userById;
    }

    @Override
    public User getByEmail(String email) {
        User userByEmail = userDAO
                                .findUserByEmail(email)
                                .orElseThrow(NotFoundException::new);
        log.info("Gotten user by email {}", email);
        return userByEmail;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
        log.info("Saved user");
    }

}
