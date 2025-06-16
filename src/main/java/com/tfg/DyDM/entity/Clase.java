package com.tfg.DyDM.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClase;
    @Column(unique = true)
    private String nombreClase;
    @ManyToMany
    private List<Ventaja> ventajas;
    @ManyToMany
    private List<Conjuro> conjuros;
    @OneToMany
    private List<Objeto> competencias;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagenClase;
}
