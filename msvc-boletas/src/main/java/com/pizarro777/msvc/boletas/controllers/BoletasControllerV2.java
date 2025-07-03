package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.assemblers.BoletasModelAssembler;
import com.pizarro777.msvc.boletas.assemblers.BoletasProductoDTOModelAssembler;
import com.pizarro777.msvc.boletas.dtos.BoletasProductoDTO;
import com.pizarro777.msvc.boletas.services.BoletasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/boletas")
@Validated
@Tag(name = "Boletas V2", description = "Operaciones CRUD de boletas hateoas")
public class BoletasControllerV2 {

    @Autowired
    private BoletasService boletasService;

    @Autowired
    private BoletasModelAssembler boletasModelAssembler;

    @Autowired
    private BoletasProductoDTOModelAssembler boletasProductoDTOModelAssembler;

    // Simulación de base de datos en memoria
    private List<BoletasProductoDTO> productosDB = List.of(
            BoletasProductoDTO.builder()
                    .idProducto(1L)
                    .nombreProducto("Arroz")
                    .marcaProducto("SuperMarca")
                    .descripcion("Arroz Grado 1")
                    .precio(1500.0)
                    .fechaCreacion(LocalDate.now())
                    .stock(2)
                    .build(),
            BoletasProductoDTO.builder()
                    .idProducto(2L)
                    .nombreProducto("Leche")
                    .marcaProducto("LaVaquita")
                    .descripcion("Leche entera 1L")
                    .precio(1000.0)
                    .fechaCreacion(LocalDate.now())
                    .stock(1)
                    .build()
    );

    @GetMapping("/productos")
    @Operation(summary = "Obtiene todos los productos", description = "Devuelve una lista de productos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = BoletasProductoDTO.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<BoletasProductoDTO>>> findAllBoletasProductos() {
        List<EntityModel<BoletasProductoDTO>> entityModels = productosDB
                .stream()
                .map(boletasProductoDTOModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<BoletasProductoDTO>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(BoletasControllerV2.class).findAllBoletasProductos()).withSelfRel()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/productos/{id}")
    @Operation(summary = "Obtiene un producto por ID", description = "Devuelve el producto con el ID dado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = BoletasProductoDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "ID único del producto", required = true)
    })
    public ResponseEntity<EntityModel<BoletasProductoDTO>> findBoletasProductoDTOById(@PathVariable Long id) {
        // Simula la búsqueda en la lista
        BoletasProductoDTO producto = productosDB.stream()
                .filter(p -> p.getIdProducto().equals(id))
                .findFirst()
                .orElse(BoletasProductoDTO.builder()
                        .idProducto(id)
                        .nombreProducto("Producto " + id)
                        .marcaProducto("MarcaGenérica")
                        .descripcion("Descripción genérica")
                        .precio(1200.0)
                        .fechaCreacion(LocalDate.now())
                        .stock(1)
                        .build()
                );

        EntityModel<BoletasProductoDTO> entityModel = boletasProductoDTOModelAssembler.toModel(producto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }
}
