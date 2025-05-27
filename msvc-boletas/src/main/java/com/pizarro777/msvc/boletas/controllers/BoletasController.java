package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.services.BoletasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@Validated
public class BoletasController {

    @Autowired
    private BoletasService boletasService;

    @GetMapping
    public ResponseEntity<List<BoletasModel>> findAll(){
        List<BoletasModel> boletas = boletasService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BoletasModel> findById(@PathVariable Long idBoletas) {
        BoletasModel proveedorModel = boletasService.findById(idBoletas);
        if (proveedorModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedorModel);
    }

    @PostMapping
    public ResponseEntity<BoletasModel> save(@RequestBody @Valid BoletasModel proveedor) {
        BoletasModel nuevaBoletas = boletasService.save(proveedor);
        return ResponseEntity.status(201).body(nuevaBoletas);
    }

}
