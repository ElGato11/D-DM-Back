package com.tfg.DyDM.service;

import com.tfg.DyDM.model.Personaje;
import com.tfg.DyDM.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    public List<Personaje> findAll() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> findById(int id) {
        return personajeRepository.findById(id);
    }

    public Personaje save(Personaje personaje) {
        if (personajeRepository.existsByNombre(personaje.getNombre())) {
            throw new RuntimeException("Ya existe un personaje con ese nombre.");
        }
        return personajeRepository.save(personaje);
    }

    public Personaje update(int id, Personaje personajeDetails) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado con id " + id));

        personaje.setNombre(personajeDetails.getNombre());
        personaje.setCarisma(personajeDetails.getCarisma());
        personaje.setFuerza(personajeDetails.getFuerza());
        personaje.setInteligencia(personajeDetails.getInteligencia());
        personaje.setSabiduria(personajeDetails.getSabiduria());
        personaje.setConstitucion(personajeDetails.getConstitucion());
        personaje.setDestreza(personajeDetails.getDestreza());
        personaje.setDescripcion(personajeDetails.getDescripcion());
        personaje.setImagenPersonaje(personajeDetails.getImagenPersonaje());
        personaje.setClases(personajeDetails.getClases());
        personaje.setObjetos(personajeDetails.getObjetos());

        return personajeRepository.save(personaje);
    }

    public void delete(int id) {
        if (!personajeRepository.existsById(id)) {
            throw new RuntimeException("Personaje no encontrado con id " + id);
        }
        personajeRepository.deleteById(id);
    }
}
