package org.travel.agency.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    /**
     * Attempts to find UserDetails from SecurityContext
     * otherwise returns empty optional
     * @return Optional<UserDetails>
     * @see Optional
     * @see org.springframework.security.core.context.SecurityContext
     */
    @Override
    public Optional<UserDetails> findLoggedInUserDetails() {
        Object userDetails = SecurityContextHolder.getContext()
                                                        .getAuthentication()
                                                        .getDetails();
        if (userDetails instanceof UserDetails) {
            return Optional.of((UserDetails) userDetails);
        }
        return Optional.empty();
    }

    /**
     * The method checks the provided credentials and creates the session
     * Used while registering user
     * @param email Email of user
     * @param password Password of user
     */
    @Override
    public void autoLogin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }
}
