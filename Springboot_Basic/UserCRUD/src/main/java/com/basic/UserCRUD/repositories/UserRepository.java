package com.basic.UserCRUD.repositories;

import com.basic.UserCRUD.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Integer id(int id);
}
