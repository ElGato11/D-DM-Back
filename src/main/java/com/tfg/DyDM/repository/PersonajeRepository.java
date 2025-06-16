package com.tfg.DyDM.repository;

import com.tfg.DyDM.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    Optional<Personaje> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Personaje> findByUsuario_Id(Long id);

}
