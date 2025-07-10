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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/proveedor")
@Validated
@Tag(name = "Proveedor", description = "Esta seccion contiene los CRUD de proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping ("/api/proveedor")
    public ResponseEntity<List<Proveedor>> listar() {

        List<Proveedor> proveedoresList = new ArrayList<>();

        proveedoresList.add(new Proveedor(
                1L,
                "Proveedor Juan",
                987654321,
                "Av. Principal 123",
                "Servicio Express"
        ));

        proveedoresList.add(new Proveedor(
                2L,
                "Proveedor Pedro",
                945732864,
                "Calle Falsa 456",
                "Servicio Normal"
        ));

        return ResponseEntity.ok(proveedoresList);
    }

    @PostMapping
    @Operation(
            summary = "Crear nuevo proveedor",
            description = "Crea un nuevo proveedor con la información enviada en el cuerpo de la petición."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos")
    })
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor nuevoProveedor = proveedorService.save(proveedor);
            log.info("API: Proveedor creado exitosamente con ID: {} y nombre: {}", nuevoProveedor.getIdProveedor(), nuevoProveedor.getNombreProveedor());
            return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.warn("Error al crear proveedor debido a argumentos inválidos: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Ocurrió un error inesperado al crear el proveedor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtiene un proveedor por ID", description = "A través del id suministrado devuelve el proveedor con esa id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proveedor no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id único del proveedor", required = true)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.proveedorService.findById(id));
    }

    @GetMapping
    @Operation(
            summary = "Devuelve todos los proveedores",
            description = "Este metodo debe retornar un List de Proveedores, en caso " +
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los proveedores OK")
    })
    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> proveedores = proveedorService.findAll();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar proveedor",
            description = "Actualiza el proveedor que coincida con el ID proporcionado con la información enviada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado para actualizar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de proveedor a actualizar", required = true)
    })
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar proveedor",
            description = "Elimina el proveedor identificado por el ID proporcionado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado para eliminar")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID único de proveedor a eliminar", required = true)
    })
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
