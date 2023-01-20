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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public void register(final UserRequestDTO userRequestDTO) {
        if (this.userQueryRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw UserAlreadyExistsException.userAlreadyExistsException(userRequestDTO.getUsername());
        }
        final Customer customer = CustomerFactoryUtils
                .convertCustomerRequestDtoToCustomer(userRequestDTO.getCustomerRequestDTO(), new Address());
        final String encodedPassword = getEncodedPassword(userRequestDTO.getPassword());
        final User user = UserFactoryUtils.createUser(userRequestDTO, encodedPassword, customer);
        this.userCommandRepository.save(user);
    }

    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final String jwt = jwtTokenUtil.generateToken((UserCustomDetails) userDetails);
            return AuthenticationResponse.builder().token(jwt).build();
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    private String getEncodedPassword(final String password) {
        return this.passwordEncoder.encode(password);
    }
}
