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
    public Boletas findById(Long idBoletas) {
        return boletasRepository.findById(idBoletas)
                .orElseThrow(() -> new BoletasException("La boleta con id " + idBoletas + " no se encuentra en la base de datos"));
    }

    @Override
    public Boletas save (Boletas boletas) {
        return boletasRepository.save(boletas);
    }

    @Override
    @Transactional
    public void eliminarBoletas(Long idBoletas) {
        if (!boletasRepository.existsById(idBoletas)) {
            // Lanza BoletasException si la boleta no existe para eliminar
            throw new BoletasException("Boleta no encontrada para eliminar con ID: " + idBoletas);
        }
        boletasRepository.deleteById(idBoletas);
        System.out.println("DEBUG Service: Boleta eliminada con ID: " + idBoletas);
    }

}
