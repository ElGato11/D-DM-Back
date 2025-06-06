package com.tfg.DyDM.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Conjuro {
    @Id
    private Long idConjuro;
    @Column(unique = true)
    private int nombreConjuro;
    private int nivel;
    @Lob
    private String efecto;
    @ManyToMany(mappedBy = "conjuros")
    private List<Clase> clases;
    private String escuela;
    //queda pendiente filtro por tipo
}
