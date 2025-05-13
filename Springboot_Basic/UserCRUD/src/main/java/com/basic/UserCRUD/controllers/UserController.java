package com.basic.UserCRUD.controllers;

import com.basic.UserCRUD.dtos.UserDTOs;
import com.basic.UserCRUD.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    private final UserServices userServices;

    @GetMapping
    public List<UserDTOs> getAllUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTOs getUser(@PathVariable int id) {
        return userServices.getUserById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody UserDTOs userDTOs) {
        userServices.addUser(userDTOs);
    }

    @PutMapping("/update/{id}")
    public UserDTOs updateUser(@PathVariable int id) {
       return userServices.updateUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userServices.deleteUser(id);
    }
}