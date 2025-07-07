package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.models.entities.Boletas;
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
            description = "Este metodo debe retornar un List de Boletas, en caso"+
                    "de que no encuentre nada retorna List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos las boletas OK")
    })

    public ResponseEntity<List<Boletas>> findAll() {
        List<Boletas> Boletas = boletasService.findAll();
        if (Boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Boletas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Boletas> findById(@PathVariable Long idBoletas) {
        Boletas boletas = boletasService.findById(idBoletas);
        if (boletas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping
    public ResponseEntity<Boletas> save(@RequestBody @Valid Boletas boletas) {
        Boletas nuevaBoletas = boletasService.save(boletas);
        return ResponseEntity.status(201).body(nuevaBoletas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBoletas(@PathVariable("id") Long id) {
        boletasService.eliminarBoletas(id);
        return ResponseEntity.noContent().build();
    }

}
