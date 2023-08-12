package com.ingenium.ingeniumecommerce.account.exception;

public class AccountRestrictedUserException extends RuntimeException{
    public AccountRestrictedUserException(String message) {
        super(message);
    }
}
