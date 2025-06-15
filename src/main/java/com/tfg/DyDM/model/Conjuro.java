package com.tfg.DyDM.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Conjuro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConjuro;
    @Column(unique = true)
    private String nombreConjuro;
    private int nivel;
    @Lob
    private String efecto;
    private String escuela;
}
