package org.travel.agency.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.config.HibernateConfig;
import org.travel.agency.config.WebConfig;
import org.travel.agency.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation = Propagation.REQUIRED)
@WebAppConfiguration
public class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    private int size;

    @Before
    public void init() {
        List<Role> roles = initRoles();
        size = roles.size();
        for (Role role : roles) {
            roleDAO.save(role);
        }
    }

    @Test
    public void testSizeOfAllRoles() {
        List<Role> roles = roleDAO.findAll();
        assertEquals(size, roles.size());
    }

    @Test
    public void testAvailableRoleExists() {
        final String CUSTOMER = "CUSTOMER";
        Optional<Role> optionalRole = roleDAO.findByName(CUSTOMER);
        assertTrue(optionalRole.isPresent());
        Role role = optionalRole.get();
        assertEquals(CUSTOMER, role.getName());
    }

    @Test
    public void testWrongRoleIsAbsent() {
        Optional<Role> foundRole = roleDAO.findByName("WRONG");
        assertFalse(foundRole.isPresent());
    }

    private List<Role> initRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(null, "CUSTOMER"));
        roles.add(new Role(null, "MANAGER"));
        return roles;
    }
}