package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Override
    public UserView findUserById(final Long userId) {
        return this.userQueryRepository.findUserById(userId)
                .orElseThrow(() -> UserNotFoundException.createForUserId(userId));
    }

    @Override
    public List<UserView> findAllUsers() {
        return this.userQueryRepository.findAllBy();
    }

    @Override
    public User findMyUser() {
        final String authenticationName = this.authenticationService.getAuthenticationName();
        return this.userQueryRepository.findByUsername(authenticationName)
                .orElseThrow(() -> UserNotFoundException.createForUserName(authenticationName));
    }


    @Override
    public Long changePassword(final String password) {
        final String authenticationName = this.authenticationService.getAuthenticationName();
        return this.userQueryRepository.findByUsername(authenticationName)
                .map(user -> user.changePassword(getEncodedPassword(password)))
                .map(User::getId)
                .orElseThrow(() -> UserNotFoundException.createForUserName(authenticationName));
    }

    @Override
    public void deleteUser(final Long userId) {
        this.userQueryRepository.deleteById(userId);
    }

    private String getEncodedPassword(final String password) {
        return this.passwordEncoder.encode(password);
    }
}