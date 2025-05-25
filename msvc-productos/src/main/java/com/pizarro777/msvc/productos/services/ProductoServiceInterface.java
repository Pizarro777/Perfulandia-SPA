package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.models.Producto;
import java.util.List;

public interface ProductoServiceInterface {
    List<Producto> findAll();
    Producto findById(Long id);
    Producto save(Producto nombre);
}
