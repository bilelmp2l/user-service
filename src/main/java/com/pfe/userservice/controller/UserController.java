package com.pfe.userservice.controller;

import com.pfe.userservice.exceptions.NotExistUserException;
import com.pfe.userservice.models.User;
import com.pfe.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Api("API method CRUD for User model")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "add User on table user")
    @PostMapping(value = "/adduser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ApiOperation(value = "get all users")
    @GetMapping(value = "/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation(value = "get user by id from database")
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "delete user by id")
    @DeleteMapping(value = "/delUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @ApiOperation(value = "get user by email")
    @GetMapping("/email/{email}")
    public User getEmail(@PathVariable("email") String email) {
        List<User> users =userService.getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email))
                 return user;
        }
        throw new NotExistUserException("user not exist");
    }

    @ApiOperation(value = "connect using login and password")
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

    @ApiOperation(value = "update user by id")
    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        User currentUser = userService.getUserById(id);

        if (currentUser == null)
            throw new NotExistUserException("user not exist");
        else {
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setEmail(user.getEmail());
            currentUser.setCountry(user.getCountry());
            currentUser.setCity(user.getCity());
            currentUser.setPhone(user.getPhone());
            currentUser.setZipcode(user.getZipcode());
            currentUser.setPassword(user.getPassword());
            return userService.saveUser(currentUser);
        }
    }

}
