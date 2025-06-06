package com.tfg.DyDM.service;

import com.tfg.DyDM.model.Conjuro;
import com.tfg.DyDM.repository.ConjuroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConjuroService {

    @Autowired
    private ConjuroRepository conjuroRepository;

    public List<Conjuro> findAll() {
        return conjuroRepository.findAll();
    }

    public Optional<Conjuro> findById(Long id) {
        return conjuroRepository.findById(id);
    }

    public Conjuro save(Conjuro conjuro) {
        if(conjuroRepository.existsByNombreConjuro(conjuro.getNombreConjuro())) {
            throw new RuntimeException("Ya existe un conjuro con ese nombre.");
        }
        return conjuroRepository.save(conjuro);
    }

    public Conjuro update(Long id, Conjuro conjuroDetails) {
        Conjuro conjuro = conjuroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conjuro no encontrado con id " + id));

        conjuro.setNombreConjuro(conjuroDetails.getNombreConjuro());
        conjuro.setNivel(conjuroDetails.getNivel());
        conjuro.setEfecto(conjuroDetails.getEfecto());
        conjuro.setEscuela(conjuroDetails.getEscuela());
        // Si quieres actualizar clases, objetos, etc. deberías manejarlo aquí

        return conjuroRepository.save(conjuro);
    }

    public void delete(Long id) {
        if(!conjuroRepository.existsById(id)) {
            throw new RuntimeException("Conjuro no encontrado con id " + id);
        }
        conjuroRepository.deleteById(id);
    }
}

