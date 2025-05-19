package com.pizarro777.msvc.proveedor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {

    @Autowired
    private proveedorServicie ProveedorService;

    @GetMapping
    public List<proveedorModel> findAll() {
        List<proveedorModel> proveedorList =proveedorService.findAll();
        if (proveedorList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedorList);
    }

}
