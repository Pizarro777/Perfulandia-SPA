package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    /* Obtiene la lista de todos los comentarios. */
    @GetMapping
    public ResponseEntity<List<ProveedorModel>> findAll() {
        List<ProveedorModel> Proveedores = proveedorService.findAll();
        if (Proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedores);
    }
    /* Busca un comentario por ID. */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProveedorModel> findById(@PathVariable Long idComentario) {
        ProveedorModel proveedorModel = proveedorService.findById(idComentario);
        if (proveedorModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedorModel);
    }
    /* Guarda nuevo comentario. */
    @PostMapping
    public ResponseEntity<ProveedorModel> save(@RequestBody @Valid ProveedorModel proveedor) {
        ProveedorModel nuevoProveedor = ProveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

}
