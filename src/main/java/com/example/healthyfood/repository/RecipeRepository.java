package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Query(value = "SELECT * FROM healthyfood.recipes r ORDER BY r.created DESC LIMIT 6", nativeQuery = true)
    List<RecipeEntity> findLastSixRecipesOrderByCreatedDesc();
}
