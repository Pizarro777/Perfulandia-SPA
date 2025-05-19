package com.msvc.detalleBoleta.msvc_detalleBoleta.controller;


import com.msvc.detalleBoleta.msvc_detalleBoleta.model.detalleBoletaModel;
import com.msvc.detalleBoleta.msvc_detalleBoleta.services.detalleBoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/detalleBoleta")
@Validated
public class detalleBoletaController {

    @Autowired
    private detalleBoletaService detalleBoletaService;

    @GetMapping
    public List<detalleBoletaModel> findAll() {
        List<detalleBoletaModel> detalleBoletaList = detalleBoletaService.findAll();
        if (detalleBoletaList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(detalleBoletaList);
    }



}
