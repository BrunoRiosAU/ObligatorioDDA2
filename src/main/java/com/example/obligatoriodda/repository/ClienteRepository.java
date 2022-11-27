package com.example.obligatoriodda.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.obligatoriodda.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
