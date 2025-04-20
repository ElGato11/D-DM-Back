package com.tfg.DyDM.service;

import com.tfg.DyDM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean existeNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existeUsuario(nombreUsuario);
    }
}
