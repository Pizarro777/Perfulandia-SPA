package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /* Crear nuevo inventario */
    @PostMapping
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        Inventario creado = inventarioService.crearInventario(inventario);
        return ResponseEntity.ok(creado);
    }

    /* Obtener inventario por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        return ResponseEntity.ok(inventario);
    }

    /* Listar todos los inventarios */
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventarios() {
        return ResponseEntity.ok(inventarioService.listarTodos());
    }

    /* Actualizar inventario por ID */
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        return ResponseEntity.ok(actualizado);
    }

    /* Eliminar inventario por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}
