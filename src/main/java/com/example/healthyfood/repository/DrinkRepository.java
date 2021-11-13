package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {

    @Query("SELECT d FROM DrinkEntity d ORDER BY d.recipe.created DESC")
    List<DrinkEntity> findAllOrderByCreatedDesc();
}
