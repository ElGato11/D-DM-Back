package com.tfg.DyDM.controller;

import com.tfg.DyDM.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/existe")
    public boolean existeNombreUsuario(@RequestParam String nombreUsuario) {
        return usuarioService.existeNombreUsuario(nombreUsuario);
    }

}
