package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventarios")
@Validated
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // Crear o actualizar stock para producto y sucursal
    @PostMapping("/stock")
    public ResponseEntity<Inventario> crearOActualizarStock(@RequestBody InventarioDto dto) {
        Inventario inventario = inventarioService.crearOActualizarStock(dto);
        return ResponseEntity.ok(inventario);
    }

    // Obtener inventario por producto y sucursal
    @GetMapping
    public ResponseEntity<Inventario> obtenerInventarioPorProductoYSucursal(
            @RequestParam Long idProducto,
            @RequestParam Long idSucursal) {

        Inventario inventario = inventarioService.obtenerPorProductoYSucursal(idProducto, idSucursal);
        return ResponseEntity.ok(inventario);
    }
}
