package com.example.obligatoriodda.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_cli", length = 8)
    private Integer id_cli;



    public Integer getId() {
        return id_cli;
    }

    public void setId(Integer id) {
        this.id_cli = id;
    }



    @Column (name="ci", length = 8)
    @NotNull(message = "Debe ingresar la cedula.")
    private  String ci;

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }


    @Column (name="nombre", length = 30)
    @NotNull(message = "Debe ingresar el nombre.")
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Column (name="apellido", length = 30)
    @NotNull(message = "Debe ingresar el apellido.")
    private String apellido;

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    @Column (name="email", length = 30)
    @Email
    @NotNull(message = "Debe ingresar el Email.")
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
})
@JoinTable(name = "Cliente_plan",
        joinColumns = @JoinColumn(name = "id_cli"),
        inverseJoinColumns = @JoinColumn(name = "id_pla")
)

private List<Plan> Planlist = new ArrayList<>();


public void addPlan(Plan plan) {
    Planlist.add(plan);
  
}
public void removeEmployee(Plan plan) {
    Planlist.remove(plan);
  
}




}


