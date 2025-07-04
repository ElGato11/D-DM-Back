package com.tfg.DyDM.repository;

import com.tfg.DyDM.entity.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ObjetoRepository extends JpaRepository<Objeto, Integer> {
    Optional<Objeto> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}

