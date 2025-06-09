package com.basic.UserCRUD.repositories;

import com.basic.UserCRUD.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Integer> {
    List<OrdersModel> findByUserId(int userId);
}
