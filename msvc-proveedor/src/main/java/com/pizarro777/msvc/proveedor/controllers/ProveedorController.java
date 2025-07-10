package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.dtos.ErrorDTO;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
@Validated
@Tag(name = "Proveedor", description = "Esta seccion contiene los CRUD de proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(
            summary = "Devuelve todos los proveedores",
            description = "Este método retorna una lista de proveedores. Si no hay proveedores, retorna una lista vacía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores retornada correctamente"),
            @ApiResponse(responseCode = "204", description = "No existen proveedores")
    })
    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> Proveedores = proveedorService.findAll();
        if (Proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedores);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener proveedor por ID",
            description = "Busca y devuelve un proveedor específico utilizando su ID único."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Porveedor no encontrado con ese ID")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del proveedor", required = true)
    })
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo proveedor", description = "Permite crear un proveedor con sus datos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Proveedor creado exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Proveedor.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Proveedor a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Proveedor.class)
            )
    )
    public ResponseEntity<Proveedor> save(@RequestBody @Valid Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar proveedor",
            description = "Actualiza el proveedor que coincida con el ID proporcionado con la información enviada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Porveedor no encontrado para actualizar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de proveedor a actualizar", required = true)
    })

    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.update(id, proveedor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar proveedor",
            description = "Elimina el proveedor identificado por el ID proporcionada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrada para eliminar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de proveedor a eliminar", required = true)
    })
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") Long idProveedor) {
        proveedorService.eliminarProveedor(idProveedor);
        return ResponseEntity.noContent().build();
    }


}
