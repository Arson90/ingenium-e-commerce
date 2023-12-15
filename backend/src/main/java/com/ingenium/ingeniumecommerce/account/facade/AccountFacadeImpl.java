package com.ingenium.ingeniumecommerce.account.facade;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.account.data.OrderData;
import com.ingenium.ingeniumecommerce.account.exception.AccountRestrictedUserException;
import com.ingenium.ingeniumecommerce.account.factory.AccountFactoryUtils;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.customer.Customer;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import com.ingenium.ingeniumecommerce.customer.CustomerService;
import com.ingenium.ingeniumecommerce.order.Order;
import com.ingenium.ingeniumecommerce.order.OrderService;
import com.ingenium.ingeniumecommerce.security.auth.AuthenticationService;
import com.ingenium.ingeniumecommerce.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {
    private static final String UNAUTHORISED_USER_MESSAGE = "Anonymous user tries to open my account page";
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final CustomerService customerService;
    private final OrderService orderService;

    @Override
    public AccountData getAccountData() {
        if (this.authenticationService.isAnonymousUser(this.authenticationService.getAuthentication())) {
            throw new AccountRestrictedUserException(UNAUTHORISED_USER_MESSAGE);
        }
        return AccountFactoryUtils.convertToAccountData(getCustomerFromUser());
    }

    @Override
    public List<OrderData> getMyOrders() {
        final List<Order> myOrders = this.orderService.findMyOrders(getCustomerFromUser());
        return AccountFactoryUtils.convertOrderToOrderData(myOrders);
    }

    @Override
    public void updateAccountCustomerData(final CustomerRequestDTO customerRequestDTO) {
        this.customerService.updateAccountCustomer(getCustomerFromUser().getId(), customerRequestDTO);
    }

    @Override
    public void updateAccountAddressData(final AddressRequestDTO addressRequestDTO) {
        this.customerService.updateAccountAddress(getCustomerFromUser().getId(), addressRequestDTO);
    }

    @Override
    public void changeAccountPassword(final String password) {
        this.userService.changePassword(password);
    }

    private Customer getCustomerFromUser() {
        return this.userService.findMyUser().getCustomer();
    }
}
