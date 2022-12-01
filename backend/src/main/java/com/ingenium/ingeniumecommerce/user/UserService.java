package com.ingenium.ingeniumecommerce.user;

import java.util.List;

public interface UserService{
    UserView findUserById(final Long userId);
    List<UserView> findAllUsers();
    void createUser(final UserDTO userDTO);
    Long changePassword(final Long userId, final String password);
    void deleteUser(final Long userId);
}