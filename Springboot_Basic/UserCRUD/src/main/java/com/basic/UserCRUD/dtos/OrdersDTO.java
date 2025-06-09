package com.basic.UserCRUD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    @NotBlank(message = "Product must not be null")
    @JsonProperty("product_name")
    private String productName;

    private int quantity;

}
