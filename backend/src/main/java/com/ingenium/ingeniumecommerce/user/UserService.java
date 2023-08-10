package com.ingenium.ingeniumecommerce.user;

import java.util.List;

public interface UserService{
    UserView findUserById(final Long userId);
    List<UserView> findAllUsers();
    User findMyUser();
    Long changePassword(final String password);
    void deleteUser(final Long userId);
}