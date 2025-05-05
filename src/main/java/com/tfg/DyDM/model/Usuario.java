package com.tfg.DyDM.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    private String nombre;
    private String clave;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Personaje> personajes;
}
