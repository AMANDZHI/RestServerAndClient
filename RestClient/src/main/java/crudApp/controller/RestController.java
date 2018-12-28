package crudApp.controller;

import crudApp.model.Role;
import crudApp.model.User;
import crudApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class RestController {

    private final String URL_USERS = "http://localhost:8080/users";

    @Autowired
    private UserService userService;

    @GetMapping("/mainRest")
    public List<User> main() {
        return userService.getUsers(URL_USERS);
    }

    @GetMapping("/findById")
    public User findById(@RequestParam Long value) {
        return userService.findById(URL_USERS, value);
    }

    @PostMapping("/updateUser")
    public User updateUser(@RequestParam String id, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String role) throws ServletException, IOException {
        User updatedUser = new User(Long.parseLong(id), email, username, password, true, Collections.singleton(Role.valueOf(role)));
        return userService.updateUser(updatedUser, URL_USERS);
    }

    @PostMapping("/addNewUser")
    public User addNewUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        User newUser = new User(email, username, password, true, Collections.singleton(Role.valueOf(role)));
        return userService.addNewUser(newUser, URL_USERS);
    }
}