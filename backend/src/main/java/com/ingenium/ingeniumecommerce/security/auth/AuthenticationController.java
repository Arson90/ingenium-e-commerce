package com.ingenium.ingeniumecommerce.security.auth;

import com.ingenium.ingeniumecommerce.user.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingenium")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody final UserRequestDTO userRequestDTO) {
        authenticationService.register(userRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody final AuthenticationRequest request) {
       final AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
       return ResponseEntity.ok(authenticationResponse);
    }
}
