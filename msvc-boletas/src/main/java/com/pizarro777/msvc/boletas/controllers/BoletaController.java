package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.BoletasModel;
import com.pizarro777.msvc.boletas.services.BoletasService;
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
@RequestMapping("/api/boletas")
@Validated
@Tag(name = "Boletas", description = "Esta seccion contiene los CRUD de boletas")
public class BoletaController {

    @Autowired
    private BoletasService boletasService;


    @GetMapping
    @Operation(
            summary = "Devuelve todas las boletas",
            description = "Este metodo debe retornar un List de Boletas, en caaso"+
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los carritos OK")
    })

    public ResponseEntity<List<BoletasModel>> findAll() {
        List<BoletasModel> Boletas = boletasService.findAll();
        if (Boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Boletas);
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
