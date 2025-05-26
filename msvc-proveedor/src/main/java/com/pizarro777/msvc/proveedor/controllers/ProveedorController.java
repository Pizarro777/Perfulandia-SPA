package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;


    @GetMapping
    public ResponseEntity<List<ProveedorModel>> findAll() {
        List<ProveedorModel> Proveedores = proveedorService.findAll();
        if (Proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProveedorModel> findById(@PathVariable Long idProveedor) {
        ProveedorModel proveedorModel = proveedorService.findById(idProveedor);
        if (proveedorModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedorModel);
    }

    @PostMapping
    public ResponseEntity<ProveedorModel> save(@RequestBody @Valid ProveedorModel proveedor) {
        ProveedorModel nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

}
