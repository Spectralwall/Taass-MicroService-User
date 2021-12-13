package com.example.microServiceUser.Repo;

import com.example.microServiceUser.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    // Select * FROM Customers WHERE email = ?
    @Query("SELECT s FROM User s WHERE s.email = ?1")
    Optional<User> findByMail(String mail);
}
