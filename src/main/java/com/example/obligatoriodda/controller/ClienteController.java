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
import com.example.obligatoriodda.repository.ClienteRepository;
import com.example.obligatoriodda.Service.ClienteServiceImp;
import com.example.obligatoriodda.Service.PlanServiceImp;;

@Controller
public class ClienteController {
    @Autowired
    ClienteServiceImp clienteServiceImp;
    @Autowired
    PlanServiceImp planServiceImp;

    @GetMapping("/listarCliente")
    public String listarClientes(Model modelo) {
        List<Cliente> clientes = clienteServiceImp.findAll();
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

        clienteServiceImp.save(cliente);
        redirect.addFlashAttribute("mensaje", "Se añadio el cliente correctamente.");
        return "redirect:/listarCliente";
    }

    @GetMapping("/{id}/editarCliente")
    public String mostrarModificarCliente(@PathVariable Integer id, Model modelo) {
        Cliente cliente = clienteServiceImp.findById(id);
        modelo.addAttribute("cliente", cliente);
        return "nuevoCliente";
    }

    @PostMapping("/{id}/editarCliente")
    public String modificarCliente(@PathVariable Integer id, @Validated Cliente cliente, BindingResult bindingResult,
            RedirectAttributes redirect, Model modelo) {
        Cliente cliDB = clienteServiceImp.findById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("cliente", cliente);
            return "nuevoCliente";
        }

        cliDB.setApellido(cliente.getApellido());
        cliDB.setCi(cliente.getCi());
        cliDB.setNombre(cliente.getNombre());
        cliDB.setEmail(cliente.getEmail());

        clienteServiceImp.save(cliDB);
        redirect.addFlashAttribute("mensaje", "Se modifico el cliente correctamente.");
        return "redirect:/listarCliente";
    }

    @PostMapping("{id}/eliminarCliente")
    public String eliminarCli(@PathVariable Integer id, RedirectAttributes redirect) {
        Cliente cliente = clienteServiceImp.findById(id);
        clienteServiceImp.delete(cliente);
        redirect.addFlashAttribute("mensaje", "Se elimino el cliente correctamente.");
        return "redirect:/listarCliente";

    }

    @GetMapping("/{id}/listarClienteXViaje")
    public String listarViajeXcliente(@PathVariable Integer id, Model modelo, RedirectAttributes redirect) {

        Cliente cliente = clienteServiceImp.findById(id);
        List<Plan> planes = cliente.ListPlan();

        if (planes.isEmpty()) {

            redirect.addFlashAttribute("mensaje", "No existe plan asignado a este cliente.");
            return "redirect:/{id}/agregarPlanes";

        } else {
            modelo.addAttribute("planes", planes);
            return "listarClienteXViaje";

        }

    }

    @PostMapping("/{id}/{idPla}/AgregarClienteAPlan")
    public String agregarClienteAViaje(@PathVariable Integer id, @PathVariable Integer idPla, RedirectAttributes redirect) {
   
        Cliente cliente = clienteServiceImp.findById(id);
        Plan uPlan = planServiceImp.findById(idPla);
      
        cliente.addPlan(uPlan);
        clienteServiceImp.save(cliente);

        redirect.addFlashAttribute("mensaje", "Se añadio el plan al cliente correctamente.");
        return "redirect:/listarCliente";
    }

    @PostMapping("{id}/{idPla}/eliminarPlanDeCliente")
    public String eliminarPlanXCliente(@PathVariable Integer id, @PathVariable Integer idPla, RedirectAttributes redirect){
        Cliente cliente = clienteServiceImp.findById(id);
        for(Plan plan : cliente.ListPlan()){
            if(plan.getId().equals(idPla)){
                cliente.removePlan(plan);
                redirect.addFlashAttribute("mensaje","Se elimino el plan correctamente");
                return "redirect:/listarCliente";
            }
        }
        redirect.addFlashAttribute("mensaje","No se pudo eliminar el plan correctamente");
        return "redirect:/listarCliente";
    }

 

      
    

}
