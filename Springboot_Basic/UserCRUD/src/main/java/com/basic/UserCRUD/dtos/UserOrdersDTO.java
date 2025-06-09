package com.basic.UserCRUD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrdersDTO {

    private UserDTO user;
    private List<OrdersDTO> orders;
}
