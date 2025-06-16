package com.tfg.DyDM.service;

import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.entity.Usuario;
import com.tfg.DyDM.repository.PersonajeRepository;
import com.tfg.DyDM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Personaje> findAll() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> findById(Long id) {
        return personajeRepository.findById(id);
    }

    public Personaje save(Personaje personaje) {
        if (personajeRepository.existsByNombre(personaje.getNombre())) {
            throw new RuntimeException("Ya existe un personaje con ese nombre.");
        }
        return personajeRepository.save(personaje);
    }

    public Personaje getById(Long id) {
        return personajeRepository.findById(id).orElse(null);
    }

    public List<Personaje> getPersonajesDeUsuario(Long userId) {
        return personajeRepository.findByUsuario_Id(userId);
    }

    public Personaje crearPersonaje(Personaje personaje, Long userId) {
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow();
        personaje.setUsuario(usuario);
        return personajeRepository.save(personaje);
    }

    public Personaje actualizarPersonaje(Long id, Personaje personajeActualizado) {
        Personaje original = personajeRepository.findById(id).orElseThrow();
        original.setNombre(personajeActualizado.getNombre());
        original.setRaza(personajeActualizado.getRaza());
        original.setClases(personajeActualizado.getClases());
        return personajeRepository.save(original);
    }

    public void borrar(Long id) {
        personajeRepository.deleteById(id);
    }


    public Personaje update(Long id, Personaje personajeDetails) {
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

    public void delete(Long id) {
        if (!personajeRepository.existsById(id)) {
            throw new RuntimeException("Personaje no encontrado con id " + id);
        }
        personajeRepository.deleteById(id);
    }
}
