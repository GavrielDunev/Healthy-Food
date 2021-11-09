package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.DessertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertRepository extends JpaRepository<DessertEntity, Long> {

    @Query("SELECT d FROM DessertEntity d ORDER BY d.recipe.created DESC")
    List<DessertEntity> findAllOrderByCreated();
}
