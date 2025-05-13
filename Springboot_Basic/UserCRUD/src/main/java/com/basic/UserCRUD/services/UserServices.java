package com.basic.UserCRUD.services;

import com.basic.UserCRUD.dtos.UserDTOs;
import com.basic.UserCRUD.exceptions.UnableToProcessException;
import com.basic.UserCRUD.exceptions.UserNotFoundException;
import com.basic.UserCRUD.models.UserModel;
import com.basic.UserCRUD.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public UserDTOs getUserById(Integer id) {
        if(id == null || id == 0) {
            throw new UnableToProcessException("ID must not be null");
        }
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " +id + " not found"));
        return modelMapper.map(userModel, UserDTOs.class);
    }

    public void addUser(UserDTOs userDTOs) {
        UserModel userModel = modelMapper.map(userDTOs, UserModel.class);
        modelMapper.map(userRepository.save(userModel), UserDTOs.class);
    }

    public UserDTOs updateUser(Integer id) {
        if(id == null || id == 0) {
            throw new UnableToProcessException("ID must not be null");
        }
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " +id + " not found"));
        userModel.setContact_no("8888888888");
        userModel.setName("Test 8");
        userModel.setEmail("test8@gmail.com");
        userRepository.save(userModel);
        return modelMapper.map(userModel, UserDTOs.class);
    }

    public void deleteUser(Integer id) {
        if(id == null || id == 0) {
            throw new UnableToProcessException("ID must not be null");
        }
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " +id + " not found"));
        userRepository.delete(userModel);
    }
}
