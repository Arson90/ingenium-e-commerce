package com.ingenium.ingeniumecommerce.account.controller;

import com.ingenium.ingeniumecommerce.account.data.AccountData;
import com.ingenium.ingeniumecommerce.account.data.OrderData;
import com.ingenium.ingeniumecommerce.account.facade.AccountFacade;
import com.ingenium.ingeniumecommerce.address.AddressRequestDTO;
import com.ingenium.ingeniumecommerce.constant.RestApiUrl;
import com.ingenium.ingeniumecommerce.customer.CustomerRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    private final AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @GetMapping(RestApiUrl.Page.MY_ACCOUNT + "/my-account-data")
    public ResponseEntity<AccountData> getAccountData() {
        final AccountData accountData = this.accountFacade.getAccountData();
        return ResponseEntity.ok(accountData);
    }

    @GetMapping(RestApiUrl.Page.MY_ACCOUNT + "/my-orders")
    ResponseEntity<List<OrderData>> getMyOrders() {
        final List<OrderData> orders = this.accountFacade.getMyOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(RestApiUrl.Page.MY_ACCOUNT + "/update-customer")
    public ResponseEntity<Void> updateAccountCustomerData(@Valid @RequestBody final CustomerRequestDTO customerRequestDTO) {
        this.accountFacade.updateAccountCustomerData(customerRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(RestApiUrl.Page.MY_ACCOUNT + "/update-address")
    public ResponseEntity<Void> updateAccountAddressData(@Valid @RequestBody final AddressRequestDTO addressRequestDTO) {
        this.accountFacade.updateAccountAddressData(addressRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(RestApiUrl.Page.MY_ACCOUNT + "/change-password")
    public ResponseEntity<Void> changeAccountPassword(@RequestBody final String password) {
        //TODO: add validation on password
        this.accountFacade.changeAccountPassword(password);
        return ResponseEntity.ok().build();
    }
}
