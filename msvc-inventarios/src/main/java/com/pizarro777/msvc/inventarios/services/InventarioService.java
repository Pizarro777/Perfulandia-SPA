package com.pizarro777.msvc.inventarios.services;

import com.pizarro777.msvc.inventarios.model.Inventario;
import com.pizarro777.msvc.inventarios.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario obtenerInventarioPorProducto(Long idProducto) {
        return inventarioRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el producto " + idProducto));
    }
    public Inventario actualizarCantidad(Long idProducto, int nuevaCantidad) {
        Inventario inventario = obtenerInventarioPorProducto(idProducto);
        inventario.setCantidad(nuevaCantidad);
        return inventarioRepository.save(inventario);
    }
}
