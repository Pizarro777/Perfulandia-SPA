package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import com.pizarro777.msvc.boletas.exceptions.BoletasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


import java.util.List;

@Service
@Transactional
public class BoletaServiceImpl implements BoletasService{

    @Autowired
    private BoletasRepository boletasRepository;

    @Override
    public List<Boletas> findAll() {
        return boletasRepository.findAll();
    }

    @Override
    public Boletas findById(Long id) {
        return boletasRepository.findById(id)
                .orElseThrow(() -> new BoletasException("La boleta con id " + id + " no se encuentra en la base de datos"));
    }

    @Override
    public Boletas save(Boletas boletas) {
        return boletasRepository.save(boletas);
    }

}
