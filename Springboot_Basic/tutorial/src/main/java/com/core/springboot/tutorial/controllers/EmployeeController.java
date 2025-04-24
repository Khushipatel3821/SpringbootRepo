package com.core.springboot.tutorial.controllers;

// Operations
// GET /employees
// POST /employees
// DELETE /employee/{id}

import com.core.springboot.tutorial.dto.EmployeesDTO;
import com.core.springboot.tutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployees() { return "Hello Employees"; }

    @GetMapping(path = "/employeesDetails/{id}")
    public EmployeesDTO getEmployeeDetails(@PathVariable("id") Long employeeId) {
        return new EmployeesDTO(employeeId, "Khushi Patel", LocalDate.of(2025, 4,23 ), true);
    }

    @GetMapping(path = "/employeesName")
    public String getEmployeeName(@RequestParam(value = "name", required = false) String employeeName,
                                  @RequestParam(value = "msg", required = false) String employeeMsg) {
        if (employeeName != null && employeeMsg != null) {
            return "Hello " + employeeName + " " + employeeMsg;
        } else if(employeeName != null) {
            return "Hello " + employeeName + " Hii";
        } else if(employeeMsg != null) {
            return "Hello Hii " + employeeMsg;
        } else {
            return "Hello Employee Welcome";
        }
    }

    @GetMapping(path = "/employeesDetailsById/{id}")
    public EmployeesDTO getEmployeeDetailsById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeesDTO addEmployee(@RequestBody EmployeesDTO employeesDTO) {
        return employeeService.addEmployee(employeesDTO);
    }

    @GetMapping(path = "/allEmployees")
    public List<EmployeesDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }
}
