package com.ingenium.ingeniumecommerce.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public static UserAlreadyExistsException userAlreadyExistsException(final String username) {
        return new UserAlreadyExistsException(String.format("User %s already exists", username));
    }
}
