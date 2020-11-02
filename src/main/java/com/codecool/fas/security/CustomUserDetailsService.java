package com.codecool.fas.security;

import com.codecool.fas.entity.UserInfo;
import com.codecool.fas.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository users;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object.
     * Spring will call this code to retrieve a user upon login from the DB.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}


