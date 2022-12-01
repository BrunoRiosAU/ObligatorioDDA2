package com.example.obligatoriodda.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.obligatoriodda.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{


    List<Plan> findByFechaLike(LocalDate fecha);
    List<Plan>findAllOrderByFecha(LocalDate fecha);
    
}
