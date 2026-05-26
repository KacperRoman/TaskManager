package com.github.kacperroman.taskmanager.controller;

import com.github.kacperroman.taskmanager.model.User;
import com.github.kacperroman.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController // REQUEST - zadania - odbieram zadania
{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {

        return userService.addUser(user);
    }
}
