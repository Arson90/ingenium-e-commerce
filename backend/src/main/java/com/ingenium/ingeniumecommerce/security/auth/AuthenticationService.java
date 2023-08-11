package com.ingenium.ingeniumecommerce.security.auth;

import com.ingenium.ingeniumecommerce.user.UserRequestDTO;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    void register(final UserRequestDTO userRequestDTO);

    AuthenticationResponse authenticate(final AuthenticationRequest request);

    boolean isAnonymousUser(final Authentication authentication);

    String getAuthenticationName();

    Authentication getAuthentication();
}
