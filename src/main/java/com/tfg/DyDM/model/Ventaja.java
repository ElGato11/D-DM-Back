package com.tfg.DyDM.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ventaja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String efecto;
}
