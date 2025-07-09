package com.pizarro777.msvc.sucursales.controllers;

import com.pizarro777.msvc.sucursales.dtos.SucursalDTO;
import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import com.pizarro777.msvc.sucursales.assemblers.SucursalModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/sucursales")
public class SucursalControllerV2 {

    @Autowired
    private SucursalService service;

    @Autowired
    private SucursalModelAssembler assembler;

    @Operation(summary = "Crear una nueva sucursal", description = "Crea una sucursal usando los datos enviados en el cuerpo de la solicitud.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<EntityModel<SucursalDTO>> crear(@RequestBody @Valid Sucursal sucursal) {
        Sucursal creada = service.crearSucursal(sucursal);

        SucursalDTO dto = new SucursalDTO();
        dto.setId(creada.getId());
        dto.setNombre(creada.getNombre());
        dto.setDireccion(creada.getDireccion());
        dto.setCiudad(creada.getCiudad());

        EntityModel<SucursalDTO> model = assembler.toModel(creada);
        return ResponseEntity
                .created(linkTo(methodOn(SucursalControllerV2.class).obtenerSucursal(creada.getId())).toUri())
                .body(model);
    }

    @Operation(summary = "Obtener una sucursal por ID", description = "Devuelve una sucursal específica si existe.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SucursalDTO>> obtenerSucursal(
            @Parameter(description = "ID único de la sucursal") @PathVariable Long id) {
        Sucursal sucursal = service.obtenerPorId(id);
        return ResponseEntity.ok(assembler.toModel(sucursal));
    }

    @Operation(summary = "Listar todas las sucursales", description = "Devuelve una colección de todas las sucursales.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SucursalDTO>>> listarTodas() {
        List<Sucursal> lista = service.listarTodas();

        List<EntityModel<SucursalDTO>> modelos = lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<SucursalDTO>> collectionModel = CollectionModel.of(
                modelos,
                linkTo(methodOn(SucursalControllerV2.class).listarTodas()).withSelfRel()
        );

        return ResponseEntity.ok(collectionModel);
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(
            @Parameter(description = "ID de la sucursal a eliminar") @PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
