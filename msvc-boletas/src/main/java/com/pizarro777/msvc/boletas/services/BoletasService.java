package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.carrito.model.Boletas;

public interface BoletasService {
    List<Boletas> findAll();
    Boletas findById(Long id);
    Boletas save(Boletas boletas);

}
