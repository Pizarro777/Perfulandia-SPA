package com.pizarro777.msvc.sucursales.controllers;

import com.pizarro777.msvc.sucursales.assemblers.SucursalModelAssembler;
import com.pizarro777.msvc.sucursales.dtos.ErrorDTO;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.services.SucursalService;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/sucursales")
@Validated
@Tag(name = "Sucursal V2", description = "Operaciones CRUD de sucursales con HATEOAS")
public class SucursalControllerV2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todas las sucursales", description = "Devuelve una lista de sucursales en el body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "204", description = "No se encontraron sucursales")
    })
    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> findAll() {
        List<EntityModel<Sucursal>> sucursales = sucursalService.findAll()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();

        if (sucursales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<Sucursal>> collectionModel = CollectionModel.of(
                sucursales,
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una sucursal por ID", description = "Devuelve una sucursal específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de la sucursal", required = true)
    })
    public ResponseEntity<EntityModel<Sucursal>> findById(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Sucursal> entityModel = sucursalModelAssembler.toModel(sucursal);
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    @Operation(summary = "Crea una nueva sucursal", description = "Guarda una nueva sucursal en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Sucursal a crear",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class))
    )
    public ResponseEntity<EntityModel<Sucursal>> create(@Valid @RequestBody Sucursal sucursal) {
        Sucursal creada = sucursalService.save(sucursal);
        EntityModel<Sucursal> entityModel = sucursalModelAssembler.toModel(creada);

        return ResponseEntity
                .created(linkTo(methodOn(SucursalControllerV2.class).findById(creada.getId())).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una sucursal", description = "Actualiza los datos de una sucursal existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Sucursal.class))),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "ID de la sucursal a actualizar", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Sucursal actualizada",
            required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class))
    )
    public ResponseEntity<EntityModel<Sucursal>> actualizarSucursal(
            @PathVariable Long id,
            @Valid @RequestBody Sucursal sucursal
    ) {
        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursal);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Sucursal> entityModel = sucursalModelAssembler.toModel(actualizada);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una sucursal", description = "Elimina una sucursal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @Parameter(name = "id", description = "ID de la sucursal a eliminar", required = true)
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }

        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
