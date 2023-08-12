package com.ingenium.ingeniumecommerce.account.facade;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.account.exception.AccountRestrictedUserException;
import com.ingenium.ingeniumecommerce.address.AddressFactoryUtils;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerFactoryUtils;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerService;
import com.ingenium.ingeniumecommerce.security.auth.AuthenticationService;
import com.ingenium.ingeniumecommerce.user.User;
import com.ingenium.ingeniumecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {
    public static final String UNAUTHORISED_USER_MESSAGE = "Anonymous user tries to open my account page";
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final CustomerService customerService;

    @Override
    public AccountData getAccountData() {
        if (this.authenticationService.isAnonymousUser(this.authenticationService.getAuthentication())) {
            throw new AccountRestrictedUserException(UNAUTHORISED_USER_MESSAGE);
        }
        return convertAccountData();
    }

    @Override
    public void updateAccountCustomerData(final CustomerRequestDTO customerRequestDTO) {
        this.customerService.updateAccountCustomer(getCustomerIdFromMyUser(), customerRequestDTO);
    }

    @Override
    public void updateAccountAddressData(final AddressRequestDTO addressRequestDTO) {
        this.customerService.updateAccountAddress(getCustomerIdFromMyUser(), addressRequestDTO);
    }

    @Override
    public void changeAccountPassword(final String password) {
        this.userService.changePassword(password);
    }

    private Long getCustomerIdFromMyUser() {
        return this.userService.findMyUser()
                .getCustomer()
                .getId();
    }

    private AccountData convertAccountData() {
        final User myUser = this.userService.findMyUser();
        return AccountData.builder()
                .username(myUser.getUsername())
                .customerData(CustomerFactoryUtils.convertCustomerToCustomerData(myUser.getCustomer()))
                .addressData(AddressFactoryUtils.convertAddressToAddressData(myUser.getCustomer().getAddress()))
                .build();
    }
}
