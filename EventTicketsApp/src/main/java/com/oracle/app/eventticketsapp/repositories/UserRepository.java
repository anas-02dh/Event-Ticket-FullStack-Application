package com.oracle.app.eventticketsapp.repositories;

import com.oracle.app.eventticketsapp.entities.User;
import com.oracle.app.eventticketsapp.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author {ANAS DR}
 **/
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findAllByRole(UserRole role);
    List<User> findByNameContainsIgnoreCase(String keyword);

    User findByEmail(String email);

    long countByRole(UserRole role);
}
