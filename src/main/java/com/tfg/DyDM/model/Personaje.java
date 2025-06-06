package com.tfg.DyDM.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPersonaje;
    @Column(unique = true)
    private String nombre;
    private int carisma;
    private int fuerza;
    private int inteligencia;
    private int sabiduria;
    private int constitucion;
    private int destreza;
    @Lob
    private String descripcion;
    @Lob
    private byte[] imagenPersonaje;
    @ManyToMany
    private List<Clase> clases;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personaje_id") // ← Esto crea la FK en la tabla Objeto
    private List<Objeto> objetos;
}
