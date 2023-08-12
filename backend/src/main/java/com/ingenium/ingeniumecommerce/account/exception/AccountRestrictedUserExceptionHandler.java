package com.ingenium.ingeniumecommerce.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountRestrictedUserExceptionHandler {
    @ResponseBody
    @ExceptionHandler(AccountRestrictedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String exceptionHandler(final AccountRestrictedUserException exception) {
        return exception.getMessage();
    }
}
