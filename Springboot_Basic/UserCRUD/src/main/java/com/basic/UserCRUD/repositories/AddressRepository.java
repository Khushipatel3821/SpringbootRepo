package com.basic.UserCRUD.repositories;

import com.basic.UserCRUD.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Integer> {
}
