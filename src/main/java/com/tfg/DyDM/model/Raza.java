package com.tfg.DyDM.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRaza;
    @Column(unique = true)
    private String nombre;
    @ManyToMany
    private List<Ventaja> ventajas;
    private int carismaMod;
    private int fuerzaMod;
    private int inteligenciaMod;
    private int sabiduriaMod;
    private int constitucionMod;
    private int destrezaMod;

}
