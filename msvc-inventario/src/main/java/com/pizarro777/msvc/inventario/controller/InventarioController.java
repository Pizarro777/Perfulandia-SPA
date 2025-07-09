package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@Validated
@Tag(name = "Inventario", description = "Esta sección contiene los CRUD de inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Obtener todos los inventarios
    @GetMapping
    @Operation(summary = "Devuelve todos los inventarios",
            description = "Retorna una lista de inventarios, o vacía si no hay datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventarios obtenidos correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron inventarios")
    })
    public ResponseEntity<List<Inventario>> findAll() {
        List<Inventario> inventarios = inventarioService.findAll();
        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }

    // Obtener inventario por ID
    @GetMapping("/{id}")
    @Operation(summary = "Devuelve un inventario por ID",
            description = "Retorna un inventario cuando se consulta por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> findById(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventario);
    }

    // Crear nuevo inventario
    @PostMapping
    public ResponseEntity<Inventario> save(@RequestBody @Valid Inventario inventario) {
        // Podrías agregar validaciones adicionales aquí si quieres
        Inventario creado = inventarioService.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar inventario por ID
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody @Valid Inventario inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar inventario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}