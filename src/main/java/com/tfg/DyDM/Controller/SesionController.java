package com.tfg.DyDM.Controller;


import com.tfg.DyDM.model.Personaje;
import com.tfg.DyDM.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logged")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SesionController {

     @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/mis-personajes")
    public ResponseEntity<List<Personaje>> obtenerMisPersonajes() {
        List<Personaje> personajes = usuarioService.getMisPersonajes();
        return ResponseEntity.ok(personajes);
    }
}
