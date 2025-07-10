package com.pizarro777.msvc.carrito.controller;

import com.pizarro777.msvc.carrito.assemblers.CarritoModelAssembler;
import com.pizarro777.msvc.carrito.dtos.ErrorDTO;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
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
@RequestMapping("/api/v2/carrito")
@Validated
@Tag(name = "Carrito V2", description = "Operaciones CRUD de carritos con HATEOAS")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoModelAssembler carritoModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos los carritos", description = "Devuelve una lista de carritos en el body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Carrito>>> findAll() {
        List<EntityModel<Carrito>> entityModels = this.carritoService.findAll()
                .stream()
                .map(carritoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Carrito>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(CarritoControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un carrito por ID", description = "Devuelve un carrito específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrito encontrado",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrito no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del carrito", required = true)
    })
    public ResponseEntity<EntityModel<Carrito>> findById(@PathVariable Long id) {
        Carrito carrito = this.carritoService.findById(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Carrito> entityModel = this.carritoModelAssembler.toModel(carrito);
        return ResponseEntity.ok(entityModel);
    }


    @PostMapping
    @Operation(summary = "Crea un nuevo carrito", description = "Permite crear un carrito con sus items")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Carrito creado exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
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
            description = "Carrito a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Carrito.class)
            )
    )
    public ResponseEntity<EntityModel<Carrito>> create(@Valid @RequestBody Carrito carrito) {
        Carrito carritoNuevo = this.carritoService.save(carrito);
        EntityModel<Carrito> entityModel = this.carritoModelAssembler.toModel(carritoNuevo);

        return ResponseEntity
                .created(linkTo(methodOn(CarritoControllerV2.class).findById(carritoNuevo.getIdCarrito())).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza un carrito existente",
            description = "Permite actualizar un carrito específico por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrito actualizado exitosamente",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Carrito.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrito no encontrado",
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
            @Parameter(name = "id", description = "ID del carrito a actualizar", required = true)
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Carrito actualizado",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Carrito.class)
            )
    )
    public ResponseEntity<EntityModel<Carrito>> actualizarCarrito(
            @PathVariable Long id,
            @Valid @RequestBody Carrito carrito
    ) {
        Carrito actualizado = carritoService.actualizarCarrito(id, carrito);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Carrito> entityModel = carritoModelAssembler.toModel(actualizado);

        return ResponseEntity.ok(entityModel);
    }


    @GetMapping("/{id}/total")
    @Operation(summary = "Obtiene el total del carrito", description = "Calcula y devuelve el total en CLP del carrito")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Total calculado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "number", format = "double")
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Carrito no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del carrito", required = true)
    })
    public ResponseEntity<Double> obtenerTotal(@PathVariable Long id) {
        Double total = this.carritoService.precioTotal(id);
        if (total == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(total);
    }
}
