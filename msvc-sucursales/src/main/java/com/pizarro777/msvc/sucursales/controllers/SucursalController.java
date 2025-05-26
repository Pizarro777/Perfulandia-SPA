package com.pizarro777.msvc.sucursales.controllers;

import com.pizarro777.msvc.sucursales.models.Sucursal;
import com.pizarro777.msvc.sucursales.repositories.SucursalRepository;
import com.pizarro777.msvc.sucursales.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@Validated
public class SucursalController {

    @Autowired
    private final SucursalService service;

    private SucursalRepository sucursalRepository;

    public SucursalController(SucursalService service) {
        this.service = service;
    }

    /* Crear una nueva Sucursal */
    @PostMapping("/make")
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody @Valid Sucursal sucursal) {
        Sucursal nueva = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(nueva);
    }

    /* Obtener una sucursal por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerSucursal(@PathVariable("id") Long id) {
        Sucursal sucursal = service.obtenerPorId(id);
        return ResponseEntity.ok(sucursal);
    }

    /* Obtener todas las sucursales */
    @GetMapping("/todas")
    public List<Sucursal> obtenerTodas() {
        return service.listarTodas();
    }

    /* Eliminar una sucursal por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

    // ENDPOINT directo al repositorio
    @GetMapping("/id/{id}")
    public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Long id) {
        return sucursalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}