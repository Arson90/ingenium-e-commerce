package com.ingenium.ingeniumecommerce.security.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private String token;
}
