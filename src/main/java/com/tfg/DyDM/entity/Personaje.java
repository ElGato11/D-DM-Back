package com.tfg.DyDM.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPersonaje;

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
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagenPersonaje;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @ManyToMany
    private List<Clase> clases;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personaje_id")
    private List<Objeto> objetos;

    @ManyToOne
    private Raza raza;
}

