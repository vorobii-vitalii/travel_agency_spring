package org.travel.agency.service;

import org.travel.agency.constants.UserRoles;
import org.travel.agency.entity.User;

public interface AuthService {
    void registerUser(User userToRegister);
    void toggleRoleOfUser(UserRoles role, User user);
}
