package com.basic.UserCRUD.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product_name")
    private String productName;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
