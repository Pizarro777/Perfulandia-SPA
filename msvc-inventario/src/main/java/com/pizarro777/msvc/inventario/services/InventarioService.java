package com.pizarro777.msvc.inventario.services;

import com.pizarro777.msvc.inventario.model.Inventario;
import java.util.List;

public interface InventarioService {

    List<Inventario> findAll();

    Inventario findById(Long id);

    Inventario save(Inventario inventario);

    Inventario actualizarInventario(Long id, Inventario inventario);

    void eliminarInventario(Long id);
}