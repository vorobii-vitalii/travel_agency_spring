package org.travel.agency.service;

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
import org.travel.agency.dao.UserDAO;
import org.travel.agency.entity.User;
import org.travel.agency.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService userService;

    private Long correctId;

    @Before
    public void init() {
        User user = new User(null, "george@gmail.com", "George", "123", null, null);
        userDAO.save(user);
        correctId = user.getId();
    }

    @Test
    public void testGetByCorrectId() {
        User user = userService.getById(correctId);
        assertEquals(correctId, user.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByWrongIdRaisesException() {
        userService.getById(correctId + 13);
    }

    @Test
    public void testGetByEmail() {
        User user = userService.getByEmail("george@gmail.com");
        assertEquals("george@gmail.com", user.getEmail());
        assertEquals("George", user.getName());
        assertEquals(correctId, user.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByNotRegisteredEmailRaisesException() {
        userService.getByEmail("someWrongEmail@gmail.com");
    }

}