package com.tfg.DyDM.controller;

import com.tfg.DyDM.DTO.AuthRequest;
import com.tfg.DyDM.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam AuthRequest formulario) {
        return usuarioService.login(formulario);
    }

}
