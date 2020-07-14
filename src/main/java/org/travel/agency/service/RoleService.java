package org.travel.agency.service;

import org.travel.agency.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getByName(String name);
}
