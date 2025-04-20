package com.tfg.DyDM.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conjuro {
    @Id
    private int nombreConjuro;
    private int nivel;
    @Lob
    private String efecto;
    @ManyToMany(mappedBy = "conjuros")
    private List<Clase> clases;
    private String escuela;
    //queda pendiente filtro por tipo
}
