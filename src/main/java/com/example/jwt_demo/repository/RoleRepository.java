package com.example.jwt_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt_demo.model.Role;
import com.example.jwt_demo.model.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

    Optional<RoleEntity> findByName(Role name);

    boolean existsByName(Role name);

} 
    
