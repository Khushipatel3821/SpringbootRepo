package com.core.springboot.tutorial.services;

import com.core.springboot.tutorial.dto.EmployeesDTO;
import com.core.springboot.tutorial.entities.EmployeeEntity;
import com.core.springboot.tutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeesDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.getReferenceById(id);
        return new EmployeesDTO(employeeEntity.getId(),
                employeeEntity.getName(),
                employeeEntity.getJoiningDate(),
                employeeEntity.isActive());
    }

    public EmployeesDTO addEmployee(EmployeesDTO employeesDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeesDTO, EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeesDTO.class);
    }

    public List<EmployeesDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeesDTO.class)).collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean isPresent = employeeRepository.existsById(employeeId);
        if (!isPresent) {
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }
}
