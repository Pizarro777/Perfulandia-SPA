package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.services.BoletasService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boletas")
@Validated
public class BoletasController {

    @Autowired
    private BoletasService boletasService;

    @GetMapping
    public ResponseEntity<List<BoletasModel>> listar(){
        List<BoletasModel> boletas = boletasService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping
    public ResponseEntity<BoletasModel> guardar(@RequestBody BoletasModel boletas) {
        BoletasModel productoNuevo = boletasService.save(boletas);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletasModel> buscar(@PathVariable Long id) {
        try {
            BoletasModel boletas = boletasService.findById(id);
            return ResponseEntity.ok(boletas);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<BoletasModel> actualizar(@PathVariable Long id, @RequestBody BoletasModel boletas) {
        try{
            BoletasModel bol = boletasService.findById(id);
            bol.setIdBoletas(id);
            bol.setNombreBoletas(boletas.getNombreBoletas());
            bol.setNumeroBoletas(boletas.getNumeroBoletas());
            bol.setCantidadBoletas(boletas.getCantidadBoletas());
            bol.setPrecioBoletas(boletas.getPrecioBoletas());

            boletasService.save(bol);
            return ResponseEntity.ok(bol);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
