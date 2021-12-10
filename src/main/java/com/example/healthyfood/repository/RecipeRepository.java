package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Query("SELECT r FROM RecipeEntity r ORDER BY r.created DESC")
    List<RecipeEntity> findRecipesOrderByCreatedDesc();
}
