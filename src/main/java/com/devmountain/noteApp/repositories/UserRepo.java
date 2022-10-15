package com.devmountain.noteApp.repositories;

import com.devmountain.noteApp.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



//THe repository layer is responsible for interacting with the database
//import org.springframework.stereotype.Repository;
//@Repository clues Spring Boot to keep track of this resource for Dependency Injection
@Repository
public interface UserRepo extends JpaRepository<User, Long>{ //JpaRepository<Type, ID_FIELD_TYPE>

    Optional<User> findByUsername(String username);
}
