package com.tfg.DyDM.model;

import com.tfg.DyDM.anotaciones.ClausulaEquipo;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Clase {
    @Id
    private String nombreClase;
    @ManyToMany
    private List<Ventaja> ventajas;
    @ManyToMany
    private List<Conjuro> conjuros;
    @ClausulaEquipo
    @OneToMany
    private List<Objeto> competencias;
}
