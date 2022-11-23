package com.ingenium.ingeniumecommerce.user;

public interface UserService{
    void createUser(final UserDTO userDTO);
    void changePassword(final Long userId, final String password);
    void deleteUser(final Long userId);
}