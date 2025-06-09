package com.basic.UserCRUD.repositories;

import com.basic.UserCRUD.models.UserModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    /*@Query("SELECT u FROM UserModel u JOIN FETCH u.address WHERE u.id = :id")
    Optional<UserModel> findUserWithAddress(@Param("id") int id);

    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.orders WHERE u.id = :id")
    Optional<UserModel> findUserWithOrders(@Param("id") int id);
*/
    // Sql query perform with JPA Query

    // SELECT * FROM table WHERE name="Khushali"
    List<UserModel> findByName(String name);

    // SELECT * FROM table WHERE name="Khushali" AND contactNo="1234567890"
    List<UserModel> findByNameAndContactNo(String name, String contactNo);

    // SELECT * FROM table WHERE name LIKE "%Khu%"
    List<UserModel> findByNameContaining(String keyword, Sort sort);

    List<UserModel> findByAddressState(String state);

    //JPQL Queries start with FROM
    @Query("FROM UserModel WHERE address.city = :city")
    List<UserModel> getUsersByCity(@Param("city") String cityName);

    @Query("FROM UserModel WHERE name = :name OR email = :email")
    List<UserModel> getUsersByNameAndEmail(@Param("name") String userName, String email);

    //JPQL Queries for UPDATE/DELETE/CREATE return type should be void/Integer and give annotation @Modifying & @Transactional (Springframework)
    @Modifying
    @Transactional
    @Query("DELETE FROM UserModel Where name = :name")
    Integer deleteUserByName(String name);
}
