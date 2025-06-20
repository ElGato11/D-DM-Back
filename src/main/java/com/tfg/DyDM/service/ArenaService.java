package com.tfg.DyDM.service;

import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.model.Sala;
import com.tfg.DyDM.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ArenaService {

    private final Map<Long, Sala> salas = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Sala> listarSalas() {
        return new ArrayList<>(salas.values());
    }

    public Sala crearSala(String nombre, Usuario host) {
        Long id = idCounter.getAndIncrement();
        Sala sala = new Sala(id, nombre, host, null, null, null, null, null, null);
        salas.put(id, sala);
        return sala;
    }

    public Sala unirseSala(Long id, Usuario jugador) {
        Sala sala = salas.get(id);
        if (sala != null && sala.getJugador2() == null) {
            sala.setJugador2(jugador);
        }
        return sala;
    }

    public void salirSala(Long id, Long userId) {
        Sala sala = salas.get(id);
        if (sala == null) return;
        if (sala.getHost() != null && sala.getHost().getId().equals(userId)) {
            sala.setHost(null);
            sala.setPersonajeHost(null);
        }
        if (sala.getJugador2() != null && sala.getJugador2().getId().equals(userId)) {
            sala.setJugador2(null);
            sala.setPersonajeJugador2(null);
        }
        if (sala.getHost() == null && sala.getJugador2() == null) {
            salas.remove(id);
        }
    }

    public Sala obtenerSala(Long id) {
        return salas.get(id);
    }

    public Sala asignarPersonaje(Long salaId, Long userId, Personaje personaje) {
        Sala sala = salas.get(salaId);
        if (sala == null) return null;
        if (sala.getHost() != null && sala.getHost().getId().equals(userId)) {
            sala.setPersonajeHost(personaje);
        } else if (sala.getJugador2() != null && sala.getJugador2().getId().equals(userId)) {
            sala.setPersonajeJugador2(personaje);
        }
        return sala;
    }

    public Sala iniciarCombate(Long id) {
        Sala sala = salas.get(id);
        sala.setHpHost(sala.getPersonajeHost().getConstitucion());
        sala.setHpJugador2(sala.getPersonajeJugador2().getConstitucion());
        sala.setTurnoActual(
                sala.getPersonajeHost().getDestreza() >= sala.getPersonajeJugador2().getDestreza()
                        ? "host" : "jugador2"
        );
        return sala;
    }

    public Sala atacar(Long id, String atacante) {
        Sala sala = salas.get(id);
        if ("host".equals(atacante)) {
            sala.setHpJugador2(sala.getHpJugador2() - 1);
            if (sala.getHpJugador2() > 0) sala.setTurnoActual("jugador2");
        } else {
            sala.setHpHost(sala.getHpHost() - 1);
            if (sala.getHpHost() > 0) sala.setTurnoActual("host");
        }
        return sala;
    }

    public Map<Long, Sala> getSalasMap() {
        return this.salas;
    }

}
