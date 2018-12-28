package amandzhi.restserver.controller;

import amandzhi.restserver.model.User;
import amandzhi.restserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class RestFullController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/users/{id}",//
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        return userService.findById(id).get();
    }

    @PutMapping(value = "/users",//
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping(value = "/users",//
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }
}