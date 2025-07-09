package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.repositories.BoletasRepository;
import com.pizarro777.msvc.boletas.exceptions.BoletasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoletaServiceImpl implements BoletasService{

    @Autowired
    private BoletasRepository boletasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Boletas> findAll() {
        return boletasRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Boletas findById(Long idBoletas) {
        return boletasRepository.findById(idBoletas)
                .orElseThrow(() -> new BoletasException("La boleta con id " + idBoletas + " no se encuentra en la base de datos"));
    }

    @Override
    @Transactional
    public Boletas save (Boletas boletas) {
        return boletasRepository.save(boletas);
    }

    @Override
    @Transactional
    public Boletas update(Long idBoletas, Boletas boletaActualizada) {

        Boletas boletaExistente = boletasRepository.findById(idBoletas)
                .orElseThrow(() -> new BoletasException("Boleta no encontrada con id " + idBoletas));

        if (boletaActualizada.getNombreBoletas() == null || boletaActualizada.getNombreBoletas().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la boleta es obligatorio para la actualización.");
        }

        Optional<Boletas> existingBoletaWithNewNameOpt = boletasRepository.findByNombreBoletas(boletaActualizada.getNombreBoletas());
        if (existingBoletaWithNewNameOpt.isPresent()) {
            Boletas boletaConMismoNombre = existingBoletaWithNewNameOpt.get();
            if (!boletaConMismoNombre.getIdBoletas().equals(idBoletas)) {
                throw new IllegalArgumentException("Ya existe otra boleta con el nombre: " + boletaActualizada.getNombreBoletas());
            }
        }
        boletaExistente.setNombreBoletas(boletaActualizada.getNombreBoletas());

        if (boletaActualizada.getPrecioBoletas() != null) {
            boletaExistente.setPrecioBoletas(boletaActualizada.getPrecioBoletas());
        }

        System.out.println("DEBUG Service: Actualizando boleta con ID: " + boletaExistente.getIdBoletas() + " Nombre: " + boletaExistente.getNombreBoletas() + " Precio: " + boletaExistente.getPrecioBoletas());

        return boletasRepository.save(boletaExistente);
    }

    @Override
    @Transactional
    public void eliminarBoletas(Long idBoletas) {
        if (!boletasRepository.existsById(idBoletas)) {
            throw new BoletasException("Boleta no encontrada para eliminar con ID: " + idBoletas);
        }
        boletasRepository.deleteById(idBoletas);
        System.out.println("DEBUG Service: Boleta eliminada con ID: " + idBoletas);
    }

}
