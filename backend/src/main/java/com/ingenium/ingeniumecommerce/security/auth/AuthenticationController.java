package com.ingenium.ingeniumecommerce.security.auth;

import com.ingenium.ingeniumecommerce.constant.RestApiUrl;
import com.ingenium.ingeniumecommerce.user.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping(RestApiUrl.Page.REGISTER)
    ResponseEntity<Void> register(@RequestBody final UserRequestDTO userRequestDTO) {
        authenticationServiceImpl.register(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(RestApiUrl.Page.LOGIN)
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody final AuthenticationRequest request) {
       final AuthenticationResponse authenticationResponse = authenticationServiceImpl.authenticate(request);
       return ResponseEntity.ok(authenticationResponse);
    }
}
