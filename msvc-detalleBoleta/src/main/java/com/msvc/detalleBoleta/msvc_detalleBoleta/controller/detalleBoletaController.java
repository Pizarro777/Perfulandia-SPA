package com.msvc.detalleBoleta.msvc_detalleBoleta.controller;

import com.msvc.detalleBoleta.msvc_detalleBoleta.dtos.DetalleBoletaDTO;
import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;
import com.msvc.detalleBoleta.msvc_detalleBoleta.services.detalleBoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detalleBoleta")
@Validated
public class detalleBoletaController {

    @Autowired
    private detalleBoletaService detalleBoletaService;

    @GetMapping
    public ResponseEntity<List<DetalleBoletaDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.detalleBoletaService.findAll());
    }

    @GetMapping("{idDetalleBoleta}")
    public ResponseEntity<List<detalleBoletaModel>> findById(@PathVariable Long idDetalleBoleta) {
        return ResponseEntity.status(HttpStatus.OK).body(this.detalleBoletaService.findById(idDetalleBoleta));
    }

    @PostMapping
    public ResponseEntity<detalleBoletaModel> save(@RequestBody @Validated detalleBoletaModel detalleBoletaMdoel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.detalleBoletaService.save(detalleBoletaMdoel));
    }
}
