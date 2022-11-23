package com.example.obligatoriodda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.obligatoriodda.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{
    
}
