package com.example.obligatoriodda.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;


import com.example.obligatoriodda.model.Plan;

public interface PlanService  {
    public List<Plan> findAll();
    public Plan findById(Integer Ci);
    public Plan save(Plan plan);
    public void delete(Plan plan);
    public List<Plan> findByFechaLike(LocalDate fecha);
    public Page<Plan> findAll(Pageable pageable);
    public List<Plan> findAllOrderByFecha(LocalDate fecha);

}