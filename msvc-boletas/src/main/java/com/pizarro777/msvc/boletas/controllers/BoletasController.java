package com.pizarro777.msvc.boletas.controllers;

import com.pizarro777.msvc.boletas.dtos.BoletasDTO;
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

    @GetMapping
    public ResponseEntity<List<BoletasDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletasDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BoletasDTO> save(@RequestBody @Valid BoletasDTO boletas){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletasService.save(boletas));
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<BoletasDTO> findByIdProducto(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletasService.findByProductoId(id));
    }

}
