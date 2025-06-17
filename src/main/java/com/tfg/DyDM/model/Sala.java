package com.tfg.DyDM.model;

import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sala {
    private Long id;
    private String nombre;
    private Usuario host;
    private Usuario jugador2;
    private Personaje personajeHost;
    private Personaje personajeJugador2;
    private Integer hpHost;
    private Integer hpJugador2;
    private String turnoActual;
}
