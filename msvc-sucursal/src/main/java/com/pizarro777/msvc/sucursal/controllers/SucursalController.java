package com.pizarro777.msvc.sucursal.controllers;

import com.pizarro777.msvc.sucursal.models.Sucursal;
import com.pizarro777.msvc.sucursal.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @PostMapping
    public ResponseEntity<Sucursal> crear(@RequestBody @Valid Sucursal sucursal) {
        return ResponseEntity.status(201).body(service.crearSucursal(sucursal));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Sucursal> listarTodas() {
        return service.listarTodas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
}