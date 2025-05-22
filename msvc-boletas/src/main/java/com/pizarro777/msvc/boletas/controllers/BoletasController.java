package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.dtos.BoletasDTO;
import com.pizarro777.msvc.boletas.models.Boletas;
import com.pizarro777.msvc.boletas.services.BoletasService;
import com.pizarro777.msvc.boletas.services.BoletasServicelmpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Boletas>> listar(){
        List<Boletas> boletas = boletasService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping
    public ResponseEntity<Boletas> guardar(@RequestBody Boletas boletas) {
        Boletas productoNuevo = boletasService.save(boletas);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boletas> buscar(@PathVariable Long id) {
        try {
            Boletas boletas = boletasService.findById(id);
            return ResponseEntity.ok(boletas);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Boletas> actualizar(@PathVariable Long id, @RequestBody Boletas boletas) {
        try{
            Boletas bol = boletasService.findById(id);
            bol.setIdBoletas(id);
            bol.setNombreBoletas(boletas.getNombreBoletas());
            bol.setNumeroBoleta(boletas.getNumeroBoleta());
            bol.setCantidadBoletas(boletas.getCantidadBoletas());
            bol.setPrecioBoletas(boletas.getPrecioBoletas());

            boletasService.save(bol);
            return ResponseEntity.ok(bol);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boletas> eliminar(@PathVariable Long id) {
        try {
            boletasService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
