package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.Boletas;

import java.util.List;

public interface BoletasService {
    List<Boletas> findAll();
    Boletas findById(int id);
    Boletas save(Boletas boletas);
}
