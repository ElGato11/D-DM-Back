package com.tfg.DyDM.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Raza {
    @Id
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
