package com.tfg.DyDM.service;

import com.tfg.DyDM.entity.Ventaja;
import com.tfg.DyDM.repository.VentajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentajaService {

    @Autowired
    private VentajaRepository ventajaRepository;

    public List<Ventaja> findAll() {
        return ventajaRepository.findAll();
    }

    public Optional<Ventaja> findById(int id) {
        return ventajaRepository.findById(id);
    }

    public Ventaja save(Ventaja ventaja) {
        if (ventajaRepository.existsByNombre(ventaja.getNombre())) {
            throw new RuntimeException("Ya existe una ventaja con ese nombre.");
        }
        return ventajaRepository.save(ventaja);
    }

    public Ventaja update(int id, Ventaja ventajaDetails) {
        Ventaja ventaja = ventajaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ventaja no encontrada con id " + id));

        ventaja.setNombre(ventajaDetails.getNombre());
        ventaja.setEfecto(ventajaDetails.getEfecto());

        return ventajaRepository.save(ventaja);
    }

    public void delete(int id) {
        if (!ventajaRepository.existsById(id)) {
            throw new RuntimeException("Ventaja no encontrada con id " + id);
        }
        ventajaRepository.deleteById(id);
    }
}

