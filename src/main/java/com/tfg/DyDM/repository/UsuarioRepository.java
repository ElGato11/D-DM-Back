package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    public Optional<Usuario> findByName(String nombreUsuario);

    Optional<UserDetails> findByNameUserDetails(String username);

    Optional<Object> findByUsername(String username);
}
