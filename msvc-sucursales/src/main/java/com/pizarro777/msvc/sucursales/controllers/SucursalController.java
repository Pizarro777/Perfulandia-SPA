package com.pizarro777.msvc.sucursales.controllers;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping("/api/sucursales")
@Validated
@Tag(name = "Sucursal", description = "CRUD para sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    @Operation(summary = "Obtiene todas las sucursales", description = "Retorna una lista de todas las sucursales registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de sucursales obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron sucursales")
    })
    public ResponseEntity<List<Sucursal>> findAll() {
        List<Sucursal> sucursales = sucursalService.findAll();
        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una sucursal por ID", description = "Devuelve una sucursal específica según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> findById(
            @Parameter(description = "ID de la sucursal a buscar", required = true)
            @PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping
    @Operation(summary = "Crea una nueva sucursal", description = "Guarda una nueva sucursal en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(schema = @Schema(implementation = Sucursal.class)))
    })
    public ResponseEntity<Sucursal> save(
            @RequestBody @Valid Sucursal sucursal) {
        Sucursal creada = sucursalService.save(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una sucursal", description = "Actualiza los datos de una sucursal existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Sucursal> actualizarSucursal(
            @PathVariable Long id,
            @RequestBody @Valid Sucursal sucursal) {
        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursal);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una sucursal", description = "Elimina una sucursal por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        Sucursal existente = sucursalService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
