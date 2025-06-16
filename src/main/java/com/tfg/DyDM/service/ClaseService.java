package com.tfg.DyDM.service;


import com.tfg.DyDM.entity.Clase;
import com.tfg.DyDM.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    public Optional<Clase> getClaseById(Long id) {
        return claseRepository.findById(id);
    }

    public Clase createClase(Clase clase) {
        if (claseRepository.existsByNombreClase(clase.getNombreClase())) {
            throw new RuntimeException("Ya existe una clase con ese nombre");
        }
        return claseRepository.save(clase);
    }

    public Clase updateClase(Long id, Clase claseDetails) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada con id: " + id));

        clase.setNombreClase(claseDetails.getNombreClase());
        clase.setVentajas(claseDetails.getVentajas());
        clase.setConjuros(claseDetails.getConjuros());
        clase.setCompetencias(claseDetails.getCompetencias());

        return claseRepository.save(clase);
    }

    public void deleteClase(Long id) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada con id: " + id));
        claseRepository.delete(clase);
    }
}