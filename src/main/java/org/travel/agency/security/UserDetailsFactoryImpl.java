package org.travel.agency.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.travel.agency.entity.Role;
import org.travel.agency.entity.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public final class UserDetailsFactoryImpl implements UserDetailsFactory {
    private static final String ROLE_PREFIX = "ROLE_";

    public UserDetails create(User user) {
        return CustomUserDetails
                    .builder()
                    .email(user.getEmail())
                    .userId(user.getId())
                    .password(user.getPassword())
                    .authorities(mapRolesToAuthorities(user.getRoles()))
                    .build();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
                .collect(Collectors.toList());
    }

}
