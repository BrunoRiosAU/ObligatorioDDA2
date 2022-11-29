package com.example.obligatoriodda.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.obligatoriodda.model.Cliente;
import com.example.obligatoriodda.model.Plan;

import com.example.obligatoriodda.Service.ClienteServiceImp;
import com.example.obligatoriodda.Service.PlanServiceImp;;

@Controller
public class ClienteController {
    @Autowired
    ClienteServiceImp serviceImp;
    @Autowired
    PlanServiceImp planServiceImp;

    @GetMapping("/listarCliente")
    public String listarClientes(Model modelo) {
        List<Cliente> clientes = serviceImp.findAll();
        modelo.addAttribute("clientes", clientes);
        return "listarCliente";
    }

    @GetMapping("/nuevoCliente")
    public String mostrarAltaCliente(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "nuevoCliente";
    }

    @PostMapping("/nuevoCliente")
    public String altaCliente(@Validated Cliente cliente, BindingResult bindingResult, RedirectAttributes redirect,
            Model modelo) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("cliente", cliente);
            return "nuevoCliente";
        }

        serviceImp.save(cliente);
        redirect.addFlashAttribute("mensaje", "Se añadio el cliente correctamente.");
        return "redirect:/";
    }

    @GetMapping("/{id}/editarCliente")
    public String mostrarModificarCliente(@PathVariable Integer id, Model modelo) {
        Cliente cliente = serviceImp.findById(id);
        modelo.addAttribute("cliente", cliente);
        return "nuevoCliente";
    }

    @PostMapping("/{id}/editarCliente")
    public String modificarCliente(@PathVariable Integer id, @Validated Cliente cliente, BindingResult bindingResult,
            RedirectAttributes redirect, Model modelo) {
        Cliente cliDB = serviceImp.findById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("cliente", cliente);
            return "pagina de editar";
        }

        cliDB.setApellido(cliente.getApellido());
        cliDB.setCi(cliente.getCi());
        cliDB.setNombre(cliente.getNombre());
        cliDB.setEmail(cliente.getEmail());

        serviceImp.save(cliDB);
        redirect.addFlashAttribute("mensaje", "Se modifico el cliente correctamente.");
        return "redirect:/listarCliente";
    }

    @PostMapping("{id}/eliminarCliente")
    public String eliminarCli(@PathVariable Integer id, RedirectAttributes redirect) {
        Cliente cliente = serviceImp.findById(id);
        serviceImp.delete(cliente);
        redirect.addFlashAttribute("mensaje", "Se elimino el cliente correctamente");
        return "redirect:/listarCliente";

    }

    @GetMapping("/{id}/listarClienteXViaje")
    public String listarViajeXcliente(@PathVariable Integer id, Model modelo, RedirectAttributes redirect) {

        Cliente cliente = serviceImp.findById(id);
        List<Plan> planes = cliente.ListPlan();

        if (planes.isEmpty()) {

            redirect.addFlashAttribute("mensaje", "No existe plan asignado a este cliente");
            return "redirect:/{id}/agregarPlanes";

        } else {
            modelo.addAttribute("planes", planes);
            return "listarClienteXViaje";

        }

    }

    @PostMapping("/{id}/{idPla}/AgregarClienteAPlan")
    public String agregarClienteAViaje(@PathVariable Integer id, @PathVariable Integer idPla,  
            RedirectAttributes redirect, Model modelo) {
   
        Cliente cliente = serviceImp.findById(id);
        Plan uPlan = planServiceImp.findById(idPla);
      
        cliente.addPlan(uPlan);
        serviceImp.save(cliente);

        redirect.addFlashAttribute("mensaje", "Se añadio el plan al cliente correctamente.");
        return "redirect:/";
    }

 

      
    

}
