package com.basic.UserCRUD.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;

    private String city;

    private String state;

    @Column(name = "pin_code")
    private String pinCode;

}
