package com.ingenium.ingeniumecommerce.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingenium")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<UserView> getUserById(@PathVariable final Long userId) {
        final UserView userView = this.userService.findUserById(userId);
        return ResponseEntity.ok(userView);
    }

    @GetMapping("/users")
    ResponseEntity<List<UserView>> getUsers() {
        List<UserView> userViewList = this.userService.findAllUsers();
        if (!userViewList.isEmpty()) {
            return ResponseEntity.ok(userViewList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    ResponseEntity<Void> userRegistration(@RequestBody final UserDTO userDTO) {
        this.userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable final Long userId) {
        this.userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
