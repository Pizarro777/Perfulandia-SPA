package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.assemblers.BoletasModelAssembler;
import com.pizarro777.msvc.boletas.dtos.ErrorDTO;
import com.pizarro777.msvc.boletas.models.entities.Boletas;
import com.pizarro777.msvc.boletas.services.BoletasService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/boletas")
@Validated
@Tag(name = "Boletas V2", description = "Operaciones CRUD de boletas con HATEOAS")
public class BoletasControllerV2 {

    @Autowired
    private BoletasService boletasService;

    @Autowired
    private BoletasModelAssembler boletasModelAssembler;

    @GetMapping
    @Operation(
            summary = "Devuelve todos las boletas",
            description = "Este método retorna una lista de boletas. Si no hay boletas, retorna una lista vacía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de boletas retornada correctamente"),
            @ApiResponse(responseCode = "204", description = "No existen boletas")
    })
    public ResponseEntity<CollectionModel<EntityModel<Boletas>>> findAll() {
        List<EntityModel<Boletas>> entityModels = this.boletasService.findAll()
                .stream()
                .map(boletasModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Boletas>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(BoletasControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una boleta por ID", description = "Devuelve una boleta específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Boleta encontrada",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Boletas.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de la boleta", required = true)
    })
    public ResponseEntity<EntityModel<Boletas>> findById(@PathVariable Long id) {
        Boletas boleta = this.boletasService.findById(id);
        EntityModel<Boletas> entityModel = this.boletasModelAssembler.toModel(boleta);
        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    @Operation(summary = "Crea una nueva boleta", description = "Permite crear una boleta con sus datos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Boleta creada exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Boletas.class)
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
            description = "Boleta a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Boletas.class)
            )
    )

    public ResponseEntity<EntityModel<Boletas>> create(@Valid @RequestBody Boletas boletas) {
        Boletas boletaNuevo = this.boletasService.save(boletas);
        EntityModel<Boletas> entityModel = this.boletasModelAssembler.toModel(boletaNuevo);

        return ResponseEntity
                .created(linkTo(methodOn(BoletasControllerV2.class).findById(boletaNuevo.getIdBoletas())).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza una boleta existente",
            description = "Permite actualizar una boleta específico por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "boleta actualizada exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Boletas.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Boleta no encontrada",
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
            @Parameter(name = "id", description = "ID de boleta a actualizar", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Boleta actualizada",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Boletas.class)
            )
    )
    public ResponseEntity<EntityModel<Boletas>> actualizarBoletas(
            @PathVariable Long id,
            @Valid @RequestBody Boletas boletas
    ) {
        Boletas actualizado = boletasService.actualizarBoletas(id, boletas);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Boletas> entityModel = boletasModelAssembler.toModel(actualizado);

        return ResponseEntity.ok(entityModel);
    }

}


