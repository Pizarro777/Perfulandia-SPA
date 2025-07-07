package com.pizarro777.msvc.inventario.services;

import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import com.pizarro777.msvc.inventario.model.Inventario;


import java.util.List;

public interface InventarioService {
    List<Inventario> listarTodos();
    List<InventarioDto> listarTodosDto();
    Inventario obtenerPorId(Long id);
    Inventario crearInventario(Inventario inventario);
    Inventario actualizarInventario(Long id, Inventario inventario);
    void eliminarInventario(Long id);
    InventarioDto obtenerPorIdDto(Long id);
    InventarioDto crearInventarioDto(InventarioDto dto);
}