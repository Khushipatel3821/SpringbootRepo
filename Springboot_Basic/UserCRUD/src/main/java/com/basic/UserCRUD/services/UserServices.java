package com.basic.UserCRUD.services;

import com.basic.UserCRUD.dtos.*;
import com.basic.UserCRUD.exceptions.UnableToProcessException;
import com.basic.UserCRUD.exceptions.UserNotFoundException;
import com.basic.UserCRUD.models.AddressModel;
import com.basic.UserCRUD.models.UserModel;
import com.basic.UserCRUD.models.requests.UserRequestModel;
import com.basic.UserCRUD.repositories.AddressRepository;
import com.basic.UserCRUD.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices {

    public UserServices(UserRepository userRepository, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    final UserRepository userRepository;
    final AddressRepository addressRepository;
    final ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id) {
        if (id == null || id == 0) {
            throw new UnableToProcessException("ID must not be null or 0");
        }
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return modelMapper.map(userModel, UserDTO.class);
    }

    public UserDTO addUser(UserDTO userDTO) {
        UserModel userModel = modelMapper.map(userDTO, UserModel.class);
        return modelMapper.map(userRepository.save(userModel), UserDTO.class);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserModel userModel = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userDTO.getId() + " not found"));
        modelMapper.map(userDTO, userModel);
        return modelMapper.map(userRepository.save(userModel), UserDTO.class);
    }

    public void deleteUser(Integer id) {
        if (id == null || id == 0) {
            throw new UnableToProcessException("ID must not be null");
        }
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        userRepository.delete(userModel);
    }

   /* public UserAddressDTO getUserWithAddress(int id) {
        UserModel userModel = userRepository.findUserWithAddress(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        AddressModel addressModel = userModel.getAddress();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());

        AddressDTO addressDTO = new AddressDTO();
        if (addressModel != null) {
            addressDTO.setCity(addressModel.getCity());
            addressDTO.setState(addressModel.getState());
            addressDTO.setPinCode(addressModel.getPinCode());
        }

        UserAddressDTO result = new UserAddressDTO();
        result.setUser(userDTO);
        result.setAddress(addressDTO);
        return result;
    }

    public UserOrdersDTO getUserWithOrders(int id) {
        UserModel userModel = userRepository.findUserWithOrders(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());

        List<OrdersDTO> orderDTOs = userModel.getOrders().stream().map(order -> {
            OrdersDTO orders = new OrdersDTO();
            orders.setProductName(order.getProductName());
            orders.setQuantity(order.getQuantity());
            return orders;
        }).toList();

        UserOrdersDTO result = new UserOrdersDTO();
        result.setUser(userDTO);
        result.setOrders(orderDTOs);

        return result;
    }

    public UserAddressDTO addUserAddress(int id, AddressDTO addressDTO) {
        // 1. Fetch user
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        // 2. Map DTO to AddressModel
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(addressDTO.getCity());
        addressModel.setState(addressDTO.getState());
        addressModel.setPinCode(addressDTO.getPinCode());

        // 3. Set relationship
        addressModel.setUser(userModel);
        userModel.setAddress(addressModel);

        // 4. Save user (cascades to address)
        userRepository.save(userModel);

        // 5. Prepare response DTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setContactNo(userModel.getContactNo());

        UserAddressDTO result = new UserAddressDTO();
        result.setUser(userDTO);
        result.setAddress(addressDTO);

        return result;

    }*/

    public List<UserDTO> getUserByName(String name) {
        return userRepository.findByName(name)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByNameAndContactNo(String name, String contactNo) {
        return userRepository.findByNameAndContactNo(name, contactNo)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByKeyWord(String keyword) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return userRepository.findByNameContaining(keyword, sort)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByNameOrEmail(String name, String email) {
        return userRepository.getUsersByNameAndEmail(name, email)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public Integer deleteUserByName(String name) {
        return userRepository.deleteUserByName(name);
    }

    public UserDTO addUserWithAddress(UserAddressDTO userAddressDTO) {
        AddressModel addressModel = new AddressModel();
        addressModel.setState(userAddressDTO.getState());
        addressModel.setCity(userAddressDTO.getCity());
        addressModel.setPinCode(userAddressDTO.getPinCode());

        addressModel = addressRepository.save(addressModel);

        UserModel userModel = new UserModel(modelMapper.map(userAddressDTO, UserRequestModel.class));
        userModel.setAddress(addressModel);

        userModel = userRepository.save(userModel);
        return modelMapper.map(userModel, UserDTO.class);
    }

    public List<UserDTO> getUserByAddressState(String state) {
        return userRepository.findByAddressState(state)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByCity(String city) {
        return userRepository.getUsersByCity(city)
                .stream()
                .map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList());
    }

}
