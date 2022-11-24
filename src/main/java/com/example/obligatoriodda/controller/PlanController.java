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

import com.example.obligatoriodda.model.Plan;
import com.example.obligatoriodda.repository.PlanRepository;

@Controller
public class PlanController {
    @Autowired
    private PlanRepository planRepository;

    @GetMapping({"/",""})
    public String mostrarIndex(Model modelo){
        return "index.html";
    }

    @GetMapping("/listarPlanes")
    public String listarPlanes(Model modelo){
        List<Plan> planes = planRepository.findAll();
        modelo.addAttribute("planes", planes);
        return "listarPlanes";
    }

    @GetMapping("/nuevoPlan")
    public String mostrarAltaPlan(Model modelo){
        modelo.addAttribute("plan", new Plan());
        return "nuevoPlan";
    }

    @PostMapping("/nuevoPlan")
    public String altaPlan(@Validated Plan plan, BindingResult bindingResult, RedirectAttributes redirect, Model modelo){
        if(bindingResult.hasErrors()){
            modelo.addAttribute("plan", plan);
            return "nuevoPlan";
        }

        planRepository.save(plan);
        redirect.addFlashAttribute("mensaje", "Se añadio el plan correctamente.");
        return "redirect:/";
    }

    @GetMapping("/{id}/editarPlan")
    public String mostrarModificarPlan(@PathVariable Integer id, Model modelo){
        Plan plan = planRepository.getReferenceById(id);
        modelo.addAttribute("plan", plan);
        return "nuevoPlan";
    }

    @PostMapping("/{id}/editarPlan")
    public String modificarPlan(@PathVariable Integer id, @Validated Plan plan, BindingResult bindingResult, RedirectAttributes redirect, Model modelo){
        Plan planDB = planRepository.getReferenceById(id);
        if(bindingResult.hasErrors()){
            modelo.addAttribute("plan", plan);
            return "pagina de editar";
        }
        
        planDB.setDestino(plan.getDestino());
        planDB.setFecha(plan.getFecha());
        planDB.setModalidad(plan.getModalidad());
        planDB.setPrecio(plan.getPrecio());
        planRepository.save(planDB);
        redirect.addFlashAttribute("mensaje", "Se modifico el plan correctamente.");
        return "redirect:/listarPlanes";
    }

    @PostMapping("{id}/eliminarPlan")
    public String eliminarPlan(@PathVariable Integer id, RedirectAttributes redirect){
        Plan plan = planRepository.getReferenceById(id);
        planRepository.delete(plan);
        redirect.addFlashAttribute("mensaje","Se elimino el plan correctamente");
        return "redirect:/listarPlanes";
        
    }
}
