package org.travel.agency.mapper;

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
import org.travel.agency.constants.UserRoles;
import org.travel.agency.exceptions.NotFoundException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class, WebConfig.class},
        loader = AnnotationConfigWebContextLoader.class
)
@Transactional(propagation= Propagation.REQUIRED)
@WebAppConfiguration
public class UserRolesMapperTest {

    @Autowired
    private Mapper<UserRoles, String> userRolesStringMapper;

    @Test
    public void testMapperTo() {
        UserRoles userRole = UserRoles.CUSTOMER;
        assertEquals("CUSTOMER", userRolesStringMapper.to(userRole));
    }

    @Test
    public void testMapperFrom() {
        String CUSTOMER = "CUSTOMER";
        assertEquals(UserRoles.CUSTOMER, userRolesStringMapper.from(CUSTOMER));
    }

    @Test(expected = NotFoundException.class)
    public void testMapperFrom2() {
        userRolesStringMapper.from("Wrong role");
    }

}