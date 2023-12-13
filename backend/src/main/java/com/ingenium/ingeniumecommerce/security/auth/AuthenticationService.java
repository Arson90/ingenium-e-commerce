package com.ingenium.ingeniumecommerce.security.auth;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    void register(final RegistrationRequest registrationRequest);

    AuthenticationResponse authenticate(final AuthenticationRequest request);

    boolean isAnonymousUser(final Authentication authentication);

    String getAuthenticationName();

    Authentication getAuthentication();
}
