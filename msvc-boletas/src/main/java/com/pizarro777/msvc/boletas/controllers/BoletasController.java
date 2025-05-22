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

}
