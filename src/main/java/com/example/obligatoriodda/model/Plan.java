package com.example.obligatoriodda.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Plan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_pla", length = 8)
    private Integer id_pla;
   

    @Column (name="destino", length = 20)
    @NotBlank(message = "Debe ingresar un destino.")
    private String destino;

    @DateTimeFormat(iso = ISO.DATE)
    @Future
    @Column (name= "fecha")
    @NotNull(message = "Debe ingresar la fecha del plan.")
    private LocalDate fecha;


    @Column (name= "modalidad")
    private String modalidad;

    @Column (name= "precio")
    private Double precio;

    public Integer getId() {
        return id_pla;
    }

    public void setId(Integer id) {
        this.id_pla = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @ManyToMany(mappedBy = "Planlist")
    public List<Cliente> departments = new ArrayList<>();
}
