package com.tfg.DyDM.service;

import com.tfg.DyDM.model.Objeto;
import com.tfg.DyDM.repository.ObjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetoService {

    @Autowired
    private ObjetoRepository objetoRepository;

    public List<Objeto> findAll() {
        return objetoRepository.findAll();
    }

    public Optional<Objeto> findById(int id) {
        return objetoRepository.findById(id);
    }

    public Objeto save(Objeto objeto) {
        if (objetoRepository.existsByNombre(objeto.getNombre())) {
            throw new RuntimeException("Ya existe un objeto con ese nombre.");
        }
        return objetoRepository.save(objeto);
    }

    public Objeto update(int id, Objeto objetoDetails) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado con id " + id));

        objeto.setNombre(objetoDetails.getNombre());
        objeto.setTipo(objetoDetails.getTipo());
        objeto.setNecesitaCompetencia(objetoDetails.isNecesitaCompetencia());
        objeto.setDanyo(objetoDetails.getDanyo());
        objeto.setEfecto(objetoDetails.getEfecto());

        return objetoRepository.save(objeto);
    }

    public void delete(int id) {
        if (!objetoRepository.existsById(id)) {
            throw new RuntimeException("Objeto no encontrado con id " + id);
        }
        objetoRepository.deleteById(id);
    }
}

