package com.oracle.app.eventticketsapp.repositories;

import com.oracle.app.eventticketsapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author {ANAS DR}
 **/
public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByName(String name);
}
