package com.basic.UserCRUD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {
    private String name;

    private String email;

    private String contactNo;

    private String city;

    private String state;

    private String pinCode;
}
