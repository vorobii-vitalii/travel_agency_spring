package org.travel.agency.mapper;

import org.springframework.stereotype.Component;
import org.travel.agency.constants.UserRoles;
import org.travel.agency.exceptions.NotFoundException;

@Component
public class UserRolesMapper implements Mapper<UserRoles, String> {

    @Override
    public UserRoles from(String s) {
        switch (s) {
            case "MANAGER":
                return UserRoles.MANAGER;
            case "CUSTOMER":
                return UserRoles.CUSTOMER;
            default:
                throw new NotFoundException();
        }
    }

    @Override
    public String to(UserRoles userRoles) {
        return userRoles.toString();
    }
}
