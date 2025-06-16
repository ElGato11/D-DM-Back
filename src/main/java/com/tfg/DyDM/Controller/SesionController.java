package com.tfg.DyDM.Controller;


import com.tfg.DyDM.jwt.JwtService;
import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.service.PersonajeService;
import com.tfg.DyDM.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/logged")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SesionController {

     @Autowired
     private final UsuarioService usuarioService;
     @Autowired
     private final PersonajeService personajeService;
     @Autowired
     private JwtService jwtService;

    @PostMapping(value = "/personaje/actualizar/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Personaje> actualizarPersonaje(
            @PathVariable Long id,
            @RequestPart("datos") Personaje personaje,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen
    ) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                personaje.setImagenPersonaje(imagen.getBytes());
            }
            Personaje actualizado = personajeService.update(id, personaje);
            return ResponseEntity.ok(actualizado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/personaje/borrar/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        try {
            personajeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/personajes")
    public List<Personaje> obtenerMisPersonajes(HttpServletRequest request) {
        Long userId = jwtService.extractUserIdFromRequest(request);
        return personajeService.getPersonajesDeUsuario(userId);
    }

    @GetMapping("/personaje/{id}")
    public ResponseEntity<Personaje> obtenerPorId(@PathVariable Long id) {
        Personaje personaje = personajeService.getById(id);
        return personaje != null ? ResponseEntity.ok(personaje) : ResponseEntity.notFound().build();
    }


    @PostMapping(value = "/personajes", consumes = "multipart/form-data")
    public ResponseEntity<Personaje> crearPersonaje(
            @RequestPart("datos") Personaje personaje,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen,
            HttpServletRequest request
    ) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                personaje.setImagenPersonaje(imagen.getBytes());
            }
            Long userId = jwtService.extractUserIdFromRequest(request);
            Personaje creado = personajeService.crearPersonaje(personaje, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
