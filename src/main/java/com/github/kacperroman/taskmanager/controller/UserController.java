package com.github.kacperroman.taskmanager.controller;

import com.github.kacperroman.taskmanager.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "user1", "password1", "user1@abc.com"));
        users.add(new User(2, "user2", "password1", "user2@abc.com"));
        users.add(new User(3, "user3", "password1", "user3@abc.com"));

        return users;
    }
}
