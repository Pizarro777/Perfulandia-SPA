package com.pizarro777.msvc.inventario.controller;


import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /* Crear nuevo inventario */
    @PostMapping
    @Operation(
            summary = "Crear nuevo inventario",
            description = "Crea un nuevo inventario con la información enviada en el cuerpo de la petición."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos")
    })
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        Inventario creado = inventarioService.crearInventario(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    /* Obtener inventario por ID */
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener inventario por ID",
            description = "Busca y devuelve un inventario específico utilizando su ID único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado con ese ID")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del inventario", required = true)
    })
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        return ResponseEntity.ok(inventario);
    }

    /* Listar todos los inventarios */
    @GetMapping
    @Operation(
            summary = "Devuelve todos los inventarios",
            description = "Este método retorna una lista de inventarios. Si no hay inventarios, retorna una lista vacía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los inventarios correctamente"),
            @ApiResponse(responseCode = "400", description = "Error - No existen inventarios")
    })
    public ResponseEntity<List<Inventario>> listarInventarios() {
        List<Inventario> inventarios = inventarioService.listarTodos();
        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);

    }

    /* Actualizar inventario por ID */
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar inventario",
            description = "Actualiza el inventario que coincida con el ID proporcionado con la información enviada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado para actualizar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del inventario a actualizar", required = true)
    })
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        return ResponseEntity.ok(actualizado);
    }

    /* Eliminar inventario por ID */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar inventario",
            description = "Elimina el inventario identificado por el ID proporcionado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado para eliminar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del inventario a eliminar", required = true)
    })
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}
