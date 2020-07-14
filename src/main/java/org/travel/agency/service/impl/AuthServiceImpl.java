package org.travel.agency.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.constants.UserRoles;
import org.travel.agency.entity.Role;
import org.travel.agency.entity.User;
import org.travel.agency.service.AuthService;
import org.travel.agency.service.RoleService;
import org.travel.agency.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public void registerUser(User userToRegister) {
        encodeUsersPassword(userToRegister);
        userToRegister.setRoles(new HashSet<>());
        attachRoleToUser(UserRoles.CUSTOMER, userToRegister);
        userService.save(userToRegister);
        log.info("User has been registered: " + userToRegister);
    }

    @Override
    public void toggleRoleOfUser(UserRoles role, User user) {
        if (userHasRole(user, role)) {
            detachRoleToUser(role, user);
        } else {
            attachRoleToUser(role, user);
        }
    }

    private boolean userHasRole(User user, UserRoles roleToCheck) {
        return user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(roleToCheck.toString()));
    }

    private void encodeUsersPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    private void detachRoleToUser(UserRoles roleToDetach, User user) {
        Set<Role> currentRoles = user.getRoles();
        Set<Role> filteredRoles = currentRoles
                .stream()
                .filter(role -> !role.getName().equals(roleToDetach.toString()))
                .collect(Collectors.toSet());
        user.setRoles(filteredRoles);
    }

    private void attachRoleToUser(UserRoles role, User user) {
        Set<Role> roles = user.getRoles();
        Role customerRole = roleService.getByName(role.toString());
        roles.add(customerRole);
        user.setRoles(roles);
    }
}
