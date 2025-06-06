package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Ventaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentajaRepository extends JpaRepository<Ventaja, Integer> {
    Optional<Ventaja> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
