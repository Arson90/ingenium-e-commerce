package com.ingenium.ingeniumecommerce.security.auth;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.security.config.JwtTokenUtil;
import com.ingenium.ingeniumecommerce.user.User;
import com.ingenium.ingeniumecommerce.user.UserAlreadyExistsException;
import com.ingenium.ingeniumecommerce.user.UserCommandRepository;
import com.ingenium.ingeniumecommerce.user.UserCustomDetails;
import com.ingenium.ingeniumecommerce.user.UserFactoryUtils;
import com.ingenium.ingeniumecommerce.user.UserQueryRepository;
import com.ingenium.ingeniumecommerce.user.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public void register(final RegistrationRequest registrationRequest) {
        if (this.userQueryRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw UserAlreadyExistsException.userAlreadyExistsException(registrationRequest.getUsername());
        }
        final Customer customer = CustomerFactoryUtils
                .convertCustomerRequestDtoToCustomer(registrationRequest.getCustomerRequestDTO(), new Address());
        final String encodedPassword = getEncodedPassword(registrationRequest.getPassword());
        final User user = UserFactoryUtils.createUser(registrationRequest, encodedPassword, customer);
        this.userCommandRepository.save(user);
    }

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        try {
            final Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final UserCustomDetails userCustomDetails = (UserCustomDetails) authenticate.getPrincipal();
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userCustomDetails.getUsername());
            final String jwt = jwtTokenUtil.generateToken((UserCustomDetails) userDetails);
            return AuthenticationResponse.builder().token(jwt).build();
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    @Override
    public boolean isAnonymousUser(final Authentication authentication) {
        return authentication.getName().equals("anonymousUser") &&
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ANONYMOUS"));
    }

    @Override
    public String getAuthenticationName() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext()
                .getAuthentication();
    }

    private String getEncodedPassword(final String password) {
        return this.passwordEncoder.encode(password);
    }
}
