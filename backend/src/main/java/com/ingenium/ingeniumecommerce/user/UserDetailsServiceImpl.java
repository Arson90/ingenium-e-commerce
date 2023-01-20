package com.ingenium.ingeniumecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserQueryRepository userQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userQueryRepository.findByUsername(username)
                .map(UserCustomDetails::new)
                .orElseThrow(() -> UserNotFoundException.createForUserName(username));
    }
}