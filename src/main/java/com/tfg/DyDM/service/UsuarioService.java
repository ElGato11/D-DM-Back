package com.tfg.DyDM.service;

import com.tfg.DyDM.DTO.AuthRequest;
import com.tfg.DyDM.model.Usuario;
import com.tfg.DyDM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean login(AuthRequest formulario) {
        Optional<Usuario> usuario = usuarioRepository.findByName(formulario.getUsername());
        String clave = usuario.map(Usuario::getClave).orElse("");
        if(clave.equals(formulario.getPassword())){
            String token = generarToken();
        }
    }

    private String generarToken() {
        return String.valueOf(System.currentTimeMillis());
    }
}
