package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.DessertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DessertRepository extends JpaRepository<DessertEntity, Long> {

}
