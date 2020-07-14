package org.travel.agency.dao;

import org.travel.agency.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDAO {
    List<Role> findAll();
    Optional<Role> findByName(String name);
    void save(Role role);
}
