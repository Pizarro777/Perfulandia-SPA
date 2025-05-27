package com.pizarro777.msvc.carrito.service;


import com.pizarro777.msvc.carrito.model.Carrito;

import java.util.List;

public interface CarritoService {
    List<Carrito> findAll();
    Carrito findById(Long id);
    Carrito save(Carrito carrito);
    void eliminarCarrito(Long id);
    Double precioTotal(Long idCarrito);

}
