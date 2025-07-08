package com.pizarro777.msvc.sucursales.controllers;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/api/v2/sucursales")
public class SucursalController {

    private final SucursalService service;
    private final SucursalRepository sucursalRepository;

    public SucursalController(SucursalService service, SucursalRepository sucursalRepository) {
        this.service = service;
        this.sucursalRepository = sucursalRepository;
    }

    @Operation(summary = "Crear una nueva sucursal",
            description = "Este endpoint permite crear una nueva sucursal en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/make")
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody @Valid Sucursal sucursal) {
        Sucursal nueva = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(nueva);
    }

    @Operation(summary = "Obtener una sucursal por su ID",
            description = "Retorna una sucursal específica a partir de su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(
            @Parameter(description = "ID único de la sucursal") @PathVariable("id") Long id) {
        Sucursal sucursal = service.obtenerPorId(id);
        return ResponseEntity.ok(sucursal);
    }

    @Operation(summary = "Listar todas las sucursales",
            description = "Devuelve un listado de todas las sucursales registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida correctamente")
    })
    @GetMapping("/todas")
    public List<Sucursal> obtenerTodas() {
        return service.listarTodas();
    }

    @Operation(summary = "Eliminar una sucursal por ID",
            description = "Elimina la sucursal correspondiente al ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucursal eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(
            @Parameter(description = "ID único de la sucursal a eliminar") @PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener sucursal directamente desde el repositorio",
            description = "Busca una sucursal por su ID directamente desde la capa del repositorio.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Sucursal> obtenerPorId(
            @Parameter(description = "ID de la sucursal") @PathVariable Long id) {
        return sucursalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}