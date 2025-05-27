package com.pizarro777.msvc.carrito.controller;


import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

     // Obtienes la lista de todos los carritos
     @GetMapping
     public ResponseEntity<List<Carrito>> findAll() {
         List<Carrito> carritos = carritoService.findAll();
         if (carritos.isEmpty()) {
             return ResponseEntity.noContent().build();
         }
            return ResponseEntity.ok(carritos);
     }

        // Busca un carrito por ID
        @GetMapping("/{id}")
        public ResponseEntity<Carrito> findById(@PathVariable("id")Long id) {
            Carrito buscarCarrito = carritoService.findById(id);
            if (buscarCarrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(buscarCarrito);
        }

        // Crear un nuevo carrito
        @PostMapping
        public ResponseEntity<Carrito> save(@RequestBody @Valid Carrito carrito) {
            if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Carrito guardarCarrito = carritoService.save(carrito);
            return ResponseEntity.status(201).body(guardarCarrito);
        }

        // Eliminar un carrito por ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCarrito(@PathVariable("id") Long id) {
            carritoService.eliminarCarrito(id);
            return ResponseEntity.noContent().build();
        }

        // Calcular el precio total de un carrito por ID
        @GetMapping("/{id}/total")
        public ResponseEntity<Double> obtenerPrecioTotal(@PathVariable Long id) {
            Double total = carritoService.precioTotal(id);
            return ResponseEntity.ok(total);
        }
}
