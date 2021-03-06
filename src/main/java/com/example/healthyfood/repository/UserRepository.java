package com.example.healthyfood.repository;

import com.example.healthyfood.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsernameIgnoreCase(String username);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u.username FROM UserEntity u")
    List<String> findAllUsernames();
}
