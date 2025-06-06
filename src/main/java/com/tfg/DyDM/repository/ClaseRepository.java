package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Clase;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    boolean existsByNombreClase(String nombreClase);
}
