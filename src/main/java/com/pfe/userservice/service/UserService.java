package com.pfe.userservice.service;

import com.pfe.userservice.exceptions.PasswordEmptyException;
import com.pfe.userservice.exceptions.UserAlreadyExistException;
import com.pfe.userservice.models.User;
import com.pfe.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        List<User> users = userRepository.findAll();
        if (user.getPassword().isEmpty()) {
            throw new PasswordEmptyException("password is empty");
        }
        if (!users.isEmpty()) {
            for (User userList : users) {
                if (user.getEmail().equals(userList.getEmail())) {
                    throw new UserAlreadyExistException("user already exist");
                }
            }
        }
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
