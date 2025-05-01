package com.basic.UserCRUD.services;

import com.basic.UserCRUD.dtos.UserDTOs;
import com.basic.UserCRUD.models.UserModel;
import com.basic.UserCRUD.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    public UserServices(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    final UserRepository userRepository;
    final ModelMapper modelMapper;

    public List<UserDTOs> getAllUsers() {
        return userRepository.findAll().stream().map(users -> modelMapper.map(users, UserDTOs.class)).collect(Collectors.toList());
    }

    public UserDTOs getUserById(@PathVariable int id) {
        UserModel userModel = userRepository.getReferenceById(id);
        return modelMapper.map(userModel, UserDTOs.class);
    }

    public void addUser(@RequestBody UserDTOs userDTOs) {
        UserModel userModel = modelMapper.map(userDTOs, UserModel.class);
        modelMapper.map(userRepository.save(userModel), UserDTOs.class);
    }

    public UserDTOs updateUser(@PathVariable int id) {
        UserModel userModel = userRepository.getReferenceById(id);
        userModel.setContact_no("8888888888");
        userModel.setName("Test 8");
        userModel.setEmail("test8@gmail.com");
        userRepository.save(userModel);
        return modelMapper.map(userModel, UserDTOs.class);
    }

    public void deleteUser(@PathVariable int id) {
        UserModel userModel = userRepository.getReferenceById(id);
        userRepository.delete(userModel);
    }
}
