package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.services.BoletasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletasService boletasService;


    @GetMapping
    public ResponseEntity<List<BoletasModel>> findAll() {
        List<BoletasModel> Proveedores = boletasService.findAll();
        if (Proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Proveedores);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BoletasModel> findById(@PathVariable Long idBoletas) {
        BoletasModel boletasModel = boletasService.findById(idBoletas);
        if (boletasModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boletasModel);
    }

    @PostMapping
    public ResponseEntity<BoletasModel> save(@RequestBody @Valid BoletasModel boleta) {
        BoletasModel nuevaBoleta = boletasService.save(boleta);
        return ResponseEntity.status(201).body(nuevaBoleta);
    }

}
