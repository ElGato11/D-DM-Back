package com.tfg.DyDM.service;

import com.tfg.DyDM.jwt.AuthRequest;
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
        Optional<Usuario> usuario = usuarioRepository.findByNombre(formulario.getUsername());
        String clave = usuario.map(Usuario::getClave).orElse("");
        if(clave.equals(formulario.getPassword())){
            String token = generarToken();
        }
        return false; //esto me lo he inventado, no lo dejes asi
    }

    private String generarToken() {
        return String.valueOf(System.currentTimeMillis());
    }
}
