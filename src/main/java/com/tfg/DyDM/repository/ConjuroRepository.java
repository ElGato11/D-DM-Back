package com.tfg.DyDM.repository;

import com.tfg.DyDM.model.Conjuro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConjuroRepository extends JpaRepository<Conjuro, Long> {
    Optional<Conjuro> findByNombreConjuro(int nombreConjuro);
    boolean existsByNombreConjuro(int nombreConjuro);
}