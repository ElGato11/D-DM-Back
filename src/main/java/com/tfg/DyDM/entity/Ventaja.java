package com.tfg.DyDM.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ventaja {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String efecto;
}
