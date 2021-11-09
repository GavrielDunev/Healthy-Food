package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.MealEntity;
import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Long> {

    @Query("SELECT m FROM MealEntity m WHERE m.category = 'BREAKFAST' ORDER BY m.recipe.created DESC")
    List<MealEntity> findAllBreakfastOrderByCreatedDesc();

    @Query("SELECT m FROM MealEntity m WHERE m.category = 'LUNCH' ORDER BY m.recipe.created DESC")
    List<MealEntity> findAllLunchOrderByCreatedDesc();

    @Query("SELECT m FROM MealEntity m WHERE m.category = 'DINNER' ORDER BY m.recipe.created DESC")
    List<MealEntity> findAllDinnerOrderByCreatedDesc();
}
