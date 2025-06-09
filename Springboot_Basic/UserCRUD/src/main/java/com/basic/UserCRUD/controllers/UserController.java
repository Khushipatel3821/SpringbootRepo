package com.basic.UserCRUD.controllers;

import com.basic.UserCRUD.dtos.UserAddressDTO;
import com.basic.UserCRUD.dtos.UserDTO;
import com.basic.UserCRUD.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<List<UserDTO>>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        return new ResponseEntity<UserDTO>(userServices.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<UserDTO>(userServices.addUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return new ResponseEntity<UserDTO>(userServices.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam int id) {
        userServices.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /*@GetMapping("/address/{id}")
    public ResponseEntity<UserAddressDTO> getUserWithAddress(@PathVariable int id) {
        return new ResponseEntity<UserAddressDTO>(userServices.getUserWithAddress(id), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<UserOrdersDTO> getUserWithOrders(@PathVariable int id) {
        return new ResponseEntity<UserOrdersDTO>(userServices.getUserWithOrders(id), HttpStatus.OK);
    }*/

    @GetMapping("/userByName")
    public ResponseEntity<List<UserDTO>> getUserByName(@RequestParam String name) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByName(name), HttpStatus.OK);
    }

    @GetMapping("/userByNameAndContactNo")
    public ResponseEntity<List<UserDTO>> getUserByNameAndContactNo(@RequestParam String name, @RequestParam String contactNo) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByNameAndContactNo(name, contactNo), HttpStatus.OK);
    }

    @GetMapping("/userByKeyword")
    public ResponseEntity<List<UserDTO>> getUserByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByKeyWord(keyword), HttpStatus.OK);
    }

    @GetMapping("/userByNameOrEmail/{name}/{email}")
    public ResponseEntity<List<UserDTO>> getUserByNameOrEmail(@PathVariable String name, @PathVariable String email) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByNameOrEmail(name, email), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable String name) {
        return new ResponseEntity<String>(userServices.deleteUserByName(name) + " No. of records deleted", HttpStatus.OK);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<UserDTO> addUserWithAddress(@Valid @RequestBody UserAddressDTO userAddressDTO) {
        return new ResponseEntity<UserDTO>(userServices.addUserWithAddress(userAddressDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getUserByState")
    public ResponseEntity<List<UserDTO>> getUserByAddressState(@RequestParam("state") String stateName) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByAddressState(stateName), HttpStatus.OK);
    }

    @GetMapping("/getUserByCity")
    public ResponseEntity<List<UserDTO>> getUserByCity(@RequestParam("city") String cityName) {
        return new ResponseEntity<List<UserDTO>>(userServices.getUserByCity(cityName), HttpStatus.OK);
    }


    //Read Properties from Application Properties class
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("/version")
    public String getAppVersion() {
        return "App version : " + appVersion;
    }
}