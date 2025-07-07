package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.assemblers.InventarioDTOModelAssembler;
import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import com.pizarro777.msvc.inventario.services.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/inventarios")
public class InventarioControllerV2 {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioDTOModelAssembler assembler;

    @GetMapping
    @Operation(
            summary = "Devuelve todos los inventarios",
            description = "Este método retorna una lista de inventarios. Si no hay inventarios, retorna una lista vacía."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventarios retornada correctamente"),
            @ApiResponse(responseCode = "204", description = "No existen inventarios")
    })
    public ResponseEntity<CollectionModel<EntityModel<InventarioDto>>> listarInventarios() {
        List<InventarioDto> lista = inventarioService.listarTodosDto();
        List<EntityModel<InventarioDto>> modelos = lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<InventarioDto>> resultado = CollectionModel.of(
                modelos,
                linkTo(methodOn(InventarioControllerV2.class).listarInventarios()).withSelfRel()
        );

        return ResponseEntity.ok(resultado);
    }
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
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<InventarioDto>> obtenerInventario(@PathVariable Long id) {
        InventarioDto dto = inventarioService.obtenerPorIdDto(id); // Mejor que el método en el service se llame así (obtenerPorIdDto)
        return ResponseEntity.ok(assembler.toModel(dto));
    }
    @Operation(
            summary = "Crear nuevo inventario",
            description = "Crea un nuevo inventario con la información enviada en el cuerpo de la petición."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos")
    })
    @PostMapping
    public ResponseEntity<EntityModel<InventarioDto>> crearInventario(@RequestBody InventarioDto dto) {
        InventarioDto creado = inventarioService.crearInventarioDto(dto);
        EntityModel<InventarioDto> modelo = assembler.toModel(creado);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).obtenerInventario(creado.getId())).toUri())
                .body(modelo);
    }

}

