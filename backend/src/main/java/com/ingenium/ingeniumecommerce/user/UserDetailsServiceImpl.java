package com.ingenium.ingeniumecommerce.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCommandRepository userCommandRepository;

    public UserDetailsServiceImpl(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userCommandRepository.findByUsername(username)
                .map(UserCustomDetails::new)
                .orElseThrow(() -> UserNotFoundException.createForUserName(username));
    }
}