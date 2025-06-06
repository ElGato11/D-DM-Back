package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {
    Optional<Raza> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

