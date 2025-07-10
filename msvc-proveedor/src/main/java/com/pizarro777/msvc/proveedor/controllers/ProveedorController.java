package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
@Validated
@Tag(name = "Proveedor", description = "Esta seccion contiene los CRUD de proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> Proveedores = proveedorService.findAll();
        if (Proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<Proveedor> save(@RequestBody @Valid Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.update(id, proveedor);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") Long idProveedor) {
        proveedorService.eliminarProveedor(idProveedor);
        return ResponseEntity.noContent().build();
    }


}
