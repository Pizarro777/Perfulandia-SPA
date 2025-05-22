package com.pizarro777.msvc.proveedor.controllers;

import com.pizarro777.msvc.proveedor.models.Proveedor;
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

    @GetMapping
    public ResponseEntity<List<proveedor>> listar(){
        List<Proveedor> boletas = proveedorService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor) {
        Proveedor productoNuevo = proveedorService.save(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscar(@PathVariable Long id) {
        try {
            Proveedor proveedor = proveedorService.findById(id);
            return ResponseEntity.ok(proveedor);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        try{
            Proveedor pro = proveedorService.findById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> eliminar(@PathVariable Long id) {
        try {
            proveedorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
