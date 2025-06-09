package com.basic.UserCRUD.models.requests;

import lombok.Data;

@Data
public class UserRequestModel {

    private String name;

    private String email;

    private String contactNo;

    private String city;

    private String state;

    private String pinCode;
}
