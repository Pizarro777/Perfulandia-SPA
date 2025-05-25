package com.pizarro777.msvc.sucursal.controllers;

import com.pizarro777.msvc.sucursal.dtos.SucursalDTO;
import com.pizarro777.msvc.sucursal.models.Sucursal;
import com.pizarro777.msvc.sucursal.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @PostMapping
    public ResponseEntity<SucursalDTO> crear(@RequestBody @Valid SucursalDTO sucursalDTO) {
        Sucursal sucursalCreada = service.crearSucursalDesdeDTO(sucursalDTO);
        SucursalDTO respuestaDTO = service.convertirASucursalDTO(sucursalCreada);
        return ResponseEntity.status(201).body(respuestaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(sucursal -> ResponseEntity.ok(service.convertirASucursalDTO(sucursal)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<SucursalDTO> listarTodas() {
        List<Sucursal> sucursales = service.listarTodas();
        return sucursales.stream()
                .map(service::convertirASucursalDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}