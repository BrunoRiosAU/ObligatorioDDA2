package com.example.obligatoriodda.Service;


import java.util.List;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.obligatoriodda.model.Cliente;
import com.example.obligatoriodda.repository.ClienteRepository;


@Service
public class ClienteServiceImp implements ClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public List<Cliente> findAll() {
      return clienteRepository.findAll();
    }

    @Override
    @Transactional
    public Cliente findById(Integer Id) {
      
        return clienteRepository.getReferenceById(Id);     
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return   clienteRepository.save(cliente);
       
    }

    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
        
    }
    @Override
    @Transactional(readOnly=true)
    public Page<Cliente> findAll(Pageable pageable) {
    return null;
    }
   

}