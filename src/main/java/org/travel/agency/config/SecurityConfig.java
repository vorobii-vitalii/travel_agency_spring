package org.travel.agency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource({"classpath:secret.properties"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService customUserDetailsService;

    public SecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/register", "errors/**")
                    .permitAll()
                .antMatchers("/hotels/**", "/rooms/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/errors/forbidden");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
