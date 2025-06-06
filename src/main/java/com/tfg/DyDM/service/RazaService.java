package com.tfg.DyDM.service;

import com.tfg.DyDM.model.Raza;
import com.tfg.DyDM.repository.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RazaService {

    @Autowired
    private RazaRepository razaRepository;

    public List<Raza> findAll() {
        return razaRepository.findAll();
    }

    public Optional<Raza> findById(Long id) {
        return razaRepository.findById(id);
    }

    public Raza save(Raza raza) {
        if (razaRepository.existsByNombre(raza.getNombre())) {
            throw new RuntimeException("Ya existe una raza con ese nombre.");
        }
        return razaRepository.save(raza);
    }

    public Raza update(Long id, Raza razaDetails) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con id " + id));

        raza.setNombre(razaDetails.getNombre());
        raza.setVentajas(razaDetails.getVentajas());
        raza.setCarismaMod(razaDetails.getCarismaMod());
        raza.setFuerzaMod(razaDetails.getFuerzaMod());
        raza.setInteligenciaMod(razaDetails.getInteligenciaMod());
        raza.setSabiduriaMod(razaDetails.getSabiduriaMod());
        raza.setConstitucionMod(razaDetails.getConstitucionMod());
        raza.setDestrezaMod(razaDetails.getDestrezaMod());

        return razaRepository.save(raza);
    }

    public void delete(Long id) {
        if (!razaRepository.existsById(id)) {
            throw new RuntimeException("Raza no encontrada con id " + id);
        }
        razaRepository.deleteById(id);
    }
}

