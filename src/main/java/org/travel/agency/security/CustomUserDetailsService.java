package org.travel.agency.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.travel.agency.dao.UserDAO;
import org.travel.agency.entity.User;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final UserDetailsFactory userDetailsFactory;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> foundUser = userDAO.findUserByEmail(email);
        if (!foundUser.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        User userToLoad = foundUser.get();
        log.info("Loaded user by email {}", email);
        return userDetailsFactory.create(userToLoad);
    }

}
