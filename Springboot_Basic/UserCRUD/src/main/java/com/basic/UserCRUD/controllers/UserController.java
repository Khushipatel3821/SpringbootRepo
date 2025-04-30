package com.basic.UserCRUD.controllers;

import com.basic.UserCRUD.models.UserModel;
import com.basic.UserCRUD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public UserModel getUser(@PathVariable int id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/users/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody UserModel userDetail) {
        userRepository.save(userDetail);
    }

    @PutMapping("/users/update/{id}")
    public UserModel updateUser(@PathVariable int id) {
        UserModel userDetails = userRepository.findById(id).get();
        userDetails.setContact_no("4444444444");
        userRepository.save(userDetails);
        return userDetails;
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        UserModel userDetails = userRepository.findById(id).get();
        userRepository.delete(userDetails);
    }
}