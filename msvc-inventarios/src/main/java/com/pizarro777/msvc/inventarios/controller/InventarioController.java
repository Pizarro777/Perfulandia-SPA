package com.pizarro777.msvc.inventarios.controller;

import com.pizarro777.msvc.inventarios.dtos.InventarioDto;
import com.pizarro777.msvc.inventarios.model.Inventario;
import com.pizarro777.msvc.inventarios.services.InventarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventarioController {

    private final InventarioService inventarioService;
    @GetMapping("/{idProducto}")
    public ResponseEntity<InventarioDto> obtenerPorProducto(@PathVariable Long idProducto) {
        Inventario inv = inventarioService.obtenerInventarioPorProducto(idProducto);
        return ResponseEntity.ok(toDto(inv));
    }
    @PutMapping("/{idProducto}")
    public ResponseEntity<Inventario> actualizarCantidad(
            @PathVariable Long idProducto,
            @RequestParam int cantidad) {
        Inventario actualizado = inventarioService.actualizarCantidad(idProducto, cantidad);
        return ResponseEntity.ok(actualizado);
    }
    private InventarioDto toDto(Inventario inventario) {
        return new InventarioDto(
                inventario.getIdProducto(),
                inventario.getCantidad(),
                inventario.getUbicacion()
        );
    }
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

}
