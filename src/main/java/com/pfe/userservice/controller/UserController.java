package com.pfe.userservice.controller;

import com.pfe.userservice.exceptions.NotExistUserException;
import com.pfe.userservice.models.User;
import com.pfe.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(value = "/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/delUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("/login")
    public User loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        User userLogged = null;
        List<User> users = userService.getUsers();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                userLogged = user;
        }
        if (userLogged == null) {
            throw new NotExistUserException("email or password is wrong");
        } else {
            return userLogged;
        }

    }

}
