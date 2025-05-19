package com.pizarro777.msvc.inventarios.controller;

import com.pizarro777.msvc.inventarios.model.Inventario;
import com.pizarro777.msvc.inventarios.services.InventarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventarioController {

    private final InventarioService inventarioService;
    @GetMapping("/{idProducto}")
    public ResponseEntity<Inventario> obtenerPorProducto(@PathVariable Long idProducto) {
        Inventario inv = inventarioService.obtenerInventarioPorProducto(idProducto);
        return ResponseEntity.ok(inv);
    }
    @PutMapping("/{idProducto}")
    public ResponseEntity<Inventario> actualizarCantidad(
            @PathVariable Long idProducto,
            @RequestParam int cantidad) {
        Inventario actualizado = inventarioService.actualizarCantidad(idProducto, cantidad);
        return ResponseEntity.ok(actualizado);
    }
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

}
