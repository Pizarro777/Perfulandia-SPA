package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.Producto;
import com.pizarro777.msvc.proveedor.models.entities.Proveedor;
import com.pizarro777.msvc.proveedor.repositories.ProveedorRepository;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }


    @GetMapping
    @Operation(
            summary = "Devuelve todos los Proveedor",
            description = "Este metodo debe retornar un List de Proveedor, en caso"+
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los Proveedor OK")
    })

    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> Proveedor = proveedorService.findAll();
        if (Proveedor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedor);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable("id") Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return ResponseEntity.of(proveedorRepository.findById(id));
    }


}
