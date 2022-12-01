package com.ingenium.ingeniumecommerce.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException createForUserId(final Long userId) {
        return new UserNotFoundException(String.format("User not found, ID: %d", userId));
    }
    public static UserNotFoundException createForUserName(final String username) {
        return new UserNotFoundException(String.format("User %s not found", username));
    }
}