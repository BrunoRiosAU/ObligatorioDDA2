package com.example.obligatoriodda.Service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;


import com.example.obligatoriodda.model.Cliente;

public interface ClienteService  {
    public List<Cliente> findAll();
    public Cliente findById(Integer Ci);
    public Cliente save(Cliente cliente);
    public void delete(Cliente cliente);
    public Page<Cliente> findAll(Pageable pageable);

}