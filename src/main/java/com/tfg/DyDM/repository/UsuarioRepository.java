package com.tfg.DyDM.repository;

import com.tfg.DyDM.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Optional<Usuario> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
