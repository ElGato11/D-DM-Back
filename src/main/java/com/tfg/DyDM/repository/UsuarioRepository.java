package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    boolean existeUsuario(String nombre);
}
