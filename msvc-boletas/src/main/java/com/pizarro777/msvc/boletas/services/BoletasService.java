package com.pizarro777.msvc.boletas.services;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
import java.util.List;

public interface BoletasService {
    List<Boletas> findAll();
    Boletas findById(Long id);
    Boletas save (Boletas boletas);
    Boletas actualizarBoletas(Long id, Boletas boletas);
    boolean eliminarBoletas(Long id);

}
