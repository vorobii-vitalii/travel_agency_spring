package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.RoleDAO;
import org.travel.agency.entity.Role;
import org.travel.agency.exceptions.NotFoundException;
import org.travel.agency.service.RoleService;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleDAO.findAll();
        log.info("Fetched all roles");
        return roles;
    }

    @Override
    public Role getByName(String name) {
        Role foundName =  roleDAO
                            .findByName(name)
                            .orElseThrow(NotFoundException::new);
        log.info("Gotten role by name {}", name);
        return foundName;
    }

}
