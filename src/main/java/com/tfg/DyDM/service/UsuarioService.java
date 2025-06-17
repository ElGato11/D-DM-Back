package com.tfg.DyDM.service;

import com.tfg.DyDM.dto.UsuarioDto;
import com.tfg.DyDM.entity.Personaje;
import com.tfg.DyDM.entity.Usuario;
import com.tfg.DyDM.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario createUsuario(UsuarioDto dto) {
        if (usuarioRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe un usuario con ese nombre.");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .clave(passwordEncoder.encode(dto.getClave()))
                .rol("USER")
                .build();

        return usuarioRepository.save(usuario);
    }

    public UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setNombre(usuario.getNombre());
        dto.setClave(usuario.getClave());
        dto.setRol(usuario.getRol());
        return dto;
    }


    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public Usuario actualizarUsuario(Long id, UsuarioDto cambios) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (cambios.getNombre() != null && !cambios.getNombre().trim().isEmpty()) {
            usuario.setNombre(cambios.getNombre().trim());
        }

        if (cambios.getClave() != null && !cambios.getClave().trim().isEmpty()) {
            usuario.setClave(cambios.getClave().trim());
        }

        if (cambios.getRol() != null && !cambios.getRol().trim().isEmpty()) {
            usuario.setRol(cambios.getRol().trim());
        }

        return usuarioRepository.save(usuario);
    }


    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public void cambiarClave(Long id, String nuevaClave) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String codificada = passwordEncoder.encode(nuevaClave);
        usuario.setClave(codificada);
        usuarioRepository.save(usuario);
    }


    public List<Personaje> getMisPersonajes() {
        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario.getPersonajes();
    }

    public Usuario createUsuarioAdmin(UsuarioDto dto) {
        if (usuarioRepository.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("Ya existe un usuario con ese nombre.");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .clave(passwordEncoder.encode(dto.getClave()))
                .rol("ADMIN")
                .build();

        return usuarioRepository.save(usuario);
    }

    public boolean existsByNombre(String nombre) {
        return usuarioRepository.existsByNombre(nombre);
    }
}