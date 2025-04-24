package com.core.springboot.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDTO {
    private Long id;
    private String name;
    private LocalDate joiningDate;
    @JsonProperty("isActive")
    private boolean isActive;

}
