package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.ProveedorModel;
import com.pizarro777.msvc.proveedor.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedor")
@Validated
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorModel>> listar(){
        List<ProveedorModel> boletas = proveedorService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping
    public ResponseEntity<ProveedorModel> guardar(@RequestBody ProveedorModel proveedor) {
        ProveedorModel productoNuevo = proveedorService.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorModel> buscar(@PathVariable Long id) {
        try {
            ProveedorModel proveedor = proveedorService.findById(id);
            return ResponseEntity.ok(proveedor);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorModel> actualizar(@PathVariable Long id, @RequestBody ProveedorModel proveedor) {
        try{
            ProveedorModel pro = proveedorService.findById(id);
            pro.setIdProveedor(id);
            pro.setTelefono(proveedor.getTelefono());
            pro.setDireccion(proveedor.getDireccion());
            pro.setServicio(proveedor.getServicio());

            proveedorService.save(pro);
            return ResponseEntity.ok(pro);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
