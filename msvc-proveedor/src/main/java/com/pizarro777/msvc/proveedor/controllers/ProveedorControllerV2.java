package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.assemblers.ProveedorModelAssembler;
import com.pizarro777.msvc.proveedor.assemblers.ProveedorProductoDTOModelAssembler;
import com.pizarro777.msvc.proveedor.dtos.ProveedorProductoDTO;
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
@RequestMapping("/api/v2/proveedor")
@Validated
@Tag(name = "Proveedor V2", description = "Operaciones CRUD de proveedor con HATEOAS")
public class ProveedorControllerV2 {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorModelAssembler proveedorModelAssembler;

    @GetMapping
    @Operation(
            summary = "Devuelve todos los proveedores",
            description = "Este método retorna una lista de proveedores. Si no hay proveedores, retorna una lista vacía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores retornada correctamente"),
            @ApiResponse(responseCode = "204", description = "No existen proveedores")
    })
    public ResponseEntity<CollectionModel<EntityModel<Proveedor>>> findAll() {
        List<EntityModel<Proveedor>> entityModels = proveedorService.findAll()
                .stream()
                .map(proveedorModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Proveedor>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProveedorControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un proveedor por ID", description = "Devuelve un proveedor específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Proveedor encontrado",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Proveedor.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proveedor no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único del proveedor", required = true)
    })
    public ResponseEntity<EntityModel<Proveedor>> findById(@PathVariable Long id) {
        Proveedor proveedor = this.proveedorService.findById(id);
        EntityModel<Proveedor> entityModel = this.proveedorModelAssembler.toModel(proveedor);
        return ResponseEntity.ok(entityModel);
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
    public ResponseEntity<EntityModel<Proveedor>> save(@Valid @RequestBody Proveedor proveedor) {
        Proveedor proveedorNuevo = this.proveedorService.save(proveedor);
        EntityModel<Proveedor> entityModel = this.proveedorModelAssembler.toModel(proveedorNuevo);

        return ResponseEntity
                .created(linkTo(methodOn(ProveedorControllerV2.class).findById(proveedorNuevo.getIdProveedor())).toUri())
                .body(entityModel);
    }

}
