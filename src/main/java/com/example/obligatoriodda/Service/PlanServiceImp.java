package com.example.obligatoriodda.Service;


import java.time.LocalDate;
import java.util.List;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.obligatoriodda.model.Plan;

import com.example.obligatoriodda.repository.PlanRepository;


@Service
public class PlanServiceImp implements PlanService{
    
    @Autowired
    private PlanRepository planRepository;

    @Override
    @Transactional
    public List<Plan> findAll() {
      return planRepository.findAll();
    }

    @Override
    @Transactional
    public Plan findById(Integer Id) {
      
        return planRepository.getReferenceById(Id);     
    }

    @Override
    @Transactional
    public Plan save(Plan plan) {
        return   planRepository.save(plan);
       
    }

    @Override
    @Transactional
    public void delete(Plan plan) {
        planRepository.delete(plan);
        
    }
    @Override
    @Transactional(readOnly=true)
    public Page<Plan> findAll(Pageable pageable) {
    return null;
    }
   
   
    @Override
    @Transactional(readOnly=true)
    public List<Plan> findAllOrderByFecha(LocalDate fecha)
    {
        return planRepository.findAllOrderByFecha(fecha);
    }

    @Override
    public List<Plan> findByFechaLike(LocalDate fecha) {
        // TODO Auto-generated method stub
        return null;
    }

}