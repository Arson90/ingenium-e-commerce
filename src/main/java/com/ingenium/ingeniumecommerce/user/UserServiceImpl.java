package com.ingenium.ingeniumecommerce.user;

import com.ingenium.ingeniumecommerce.address.Address;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserCommandRepository userCommandRepository;
    private final UserQueryRepository userQueryRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserCommandRepository userCommandRepository, UserQueryRepository userQueryRepository, PasswordEncoder passwordEncoder) {
        this.userCommandRepository = userCommandRepository;
        this.userQueryRepository = userQueryRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
    public void createUser(final UserDTO userDTO) {
        final Customer customer = CustomerFactoryUtils.createCustomer(userDTO.getCustomerDTO());
        final Address address = new Address();
        customer.addAddressToCustomer(address);
        final String encodedPassword = getEncodedPassword(userDTO.getPassword());
        final User user = UserFactoryUtils.createUser(userDTO, encodedPassword, customer);
        this.userQueryRepository.save(user);
    }

    @Override
    public void changePassword(final Long userId, final String password) {
        this.userCommandRepository.findById(userId)
                .map(user -> user.changePassword(getEncodedPassword(password)))
                .orElseThrow(() -> UserNotFoundException.createForUserId(userId));
    }

    @Override
    public void deleteUser(final Long userId) {
        this.userQueryRepository.deleteById(userId);
    }

    private String getEncodedPassword(final String password) {
        return this.passwordEncoder.encode(password);
    }
}