package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    Optional<Personaje> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
