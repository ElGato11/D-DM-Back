package com.tfg.DyDM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String tipo;
    private boolean necesitaCompetencia;
    private String danyo;   //codifica "numeroDados/D/carasDado"
    private String efecto;
}
