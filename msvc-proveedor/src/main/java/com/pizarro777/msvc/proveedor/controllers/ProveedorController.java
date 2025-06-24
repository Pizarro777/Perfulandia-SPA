package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Devuelve todas los Proveedor",
            description = "Este metodo debe retornar un List de Proveedor, en caaso"+
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los Proveedor OK")
    })

    public ResponseEntity<List<ProveedorModel>> findAll() {
        List<ProveedorModel> Proveedor = proveedorService.findAll();
        if (Proveedor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedor);
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
