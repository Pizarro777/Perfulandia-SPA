package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.assemblers.InventarioModelAssembler;
import com.pizarro777.msvc.inventario.dtos.ErrorDTO;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/inventario")
@Validated
@Tag(name = "Inventario V2", description = "Operaciones CRUD de inventarios con HATEOAS")
public class InventarioControllerV2 {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler inventarioModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos los inventarios", description = "Devuelve una lista de inventarios en el body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No se encontraron inventarios"
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> findAll() {
        List<EntityModel<Inventario>> entityModels = this.inventarioService.findAll()
                .stream()
                .map(inventarioModelAssembler::toModel)
                .toList();

        if (entityModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<Inventario>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(InventarioControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un inventario por ID", description = "Devuelve un inventario específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventario encontrado",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del inventario", required = true)
    })
    public ResponseEntity<EntityModel<Inventario>> findById(@PathVariable Long id) {
        Inventario inventario = this.inventarioService.findById(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Inventario> entityModel = this.inventarioModelAssembler.toModel(inventario);
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo inventario", description = "Permite crear un inventario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Inventario creado exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
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
            description = "Inventario a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<EntityModel<Inventario>> create(@Valid @RequestBody Inventario inventario) {
        Inventario creado = this.inventarioService.save(inventario);
        EntityModel<Inventario> entityModel = this.inventarioModelAssembler.toModel(creado);

        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).findById(creado.getId())).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un inventario existente", description = "Actualiza un inventario por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inventario actualizado exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Inventario.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inventario no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del inventario a actualizar", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Inventario actualizado",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Inventario.class)
            )
    )
    public ResponseEntity<EntityModel<Inventario>> actualizarInventario(
            @PathVariable Long id,
            @Valid @RequestBody Inventario inventario
    ) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Inventario> entityModel = inventarioModelAssembler.toModel(actualizado);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un inventario por ID", description = "Elimina un inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado exitosamente")
    })
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}