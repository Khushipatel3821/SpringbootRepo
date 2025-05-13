package com.basic.UserCRUD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOs {
    @JsonProperty("id")
    private int id;
    @NotBlank(message = "Name must not be blank")
    @JsonProperty("name")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @JsonProperty("email")
    private String email;
    @NotNull(message = "Contact No. is required")
    @JsonProperty("contact_no")
    private String contact_no;
}