package org.travel.agency.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.travel.agency.entity.User;

public interface UserDetailsFactory {
    UserDetails create(User user);
}
