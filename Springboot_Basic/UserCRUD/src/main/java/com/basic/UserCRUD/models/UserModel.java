package com.basic.UserCRUD.models;

import com.basic.UserCRUD.models.requests.UserRequestModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    @JoinColumn(name = "address_id")
    @OneToOne()
    private AddressModel address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrdersModel> orders = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public UserModel(UserRequestModel userRequestModel) {
        this.name = userRequestModel.getName();
        this.email = userRequestModel.getEmail();
        this.contactNo = userRequestModel.getContactNo();
    }

}
