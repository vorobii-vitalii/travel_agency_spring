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
import org.travel.agency.entity.User;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    private Set<Role> roles;

    private int size;

    private Long lastId;

    @Before
    public void init() {
        initializeRoles();
        List<User> initUsers = getInitUsers();
        size = initUsers.size();
        for (User user : initUsers) {
            userDAO.save(user);
            lastId = user.getId();
        }
    }

    @Test
    public void testUsersCountMatch() {
        List<User> users = userDAO.findAll();
        assertEquals(size, users.size());
    }

    @Test
    public void testUsersByCorrectRoleCountMatch() {
        List<User> matchedUsers = userDAO.findAllByRole("CUSTOMER");
        assertEquals(size, matchedUsers.size());
    }

    @Test
    public void testUsersByWrongRoleAreAbsent() {
        List<User> matchedUsers = userDAO.findAllByRole("WRONG_ROLE");
        assertTrue(matchedUsers.isEmpty());
    }

    @Test
    public void testUserByCorrectIdExists() {
        final Long id = lastId;
        Optional<User> optionalUser = userDAO.findUserById(id);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals(id, user.getId());
    }

    @Test
    public void testUserByWrongIdIsAbsent() {
        Optional<User> optionalUser = userDAO.findUserById(-1L);
        assertFalse(optionalUser.isPresent());
    }

    @Test
    public void testUserByRegisteredEmail() {
        final String email = "george@gmail.com";
        Optional<User> optionalUser = userDAO.findUserByEmail(email);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals(email, user.getEmail());
        assertEquals("George", user.getName());
    }

    @Test
    public void testUserByNotRegisteredEmail() {
        Optional<User> optionalUser = userDAO.findUserByEmail("some_wrong_email@gmail.com");
        assertFalse(optionalUser.isPresent());
    }

    @Test
    public void testSave() {
        User user = new User(null, "kate@gmail.com", "Kate", "123", roles, null);
        userDAO.save(user);
        Long expectedId = lastId + 1;
        assertEquals(expectedId, user.getId());
        lastId++;
        size++;
    }

    private List<User> getInitUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(null, "george@gmail.com", "George", "123", roles, null));
        users.add(new User(null, "william@gmail.com", "William", "123", roles, null));
        return users;
    }

    private void initializeRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(null, "CUSTOMER"));
        roles.add(new Role(null, "MANAGER"));
        for (Role role : roles) {
            roleDAO.save(role);
        }
        this.roles = new HashSet<>(roles);
    }

}