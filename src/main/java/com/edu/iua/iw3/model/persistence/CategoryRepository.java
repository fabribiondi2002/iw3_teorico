package com.edu.iua.iw3.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.iua.iw3.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findOneByCategory(String category);

    Optional<Category> findByCategoryAndIdNot(String category, Long id);
    
}
