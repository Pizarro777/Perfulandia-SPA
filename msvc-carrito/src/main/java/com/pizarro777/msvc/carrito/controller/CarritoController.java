package com.pizarro777.msvc.carrito.controller;


import com.pizarro777.msvc.carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/carritos")
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
        public ResponseEntity<Carrito> findById(Long id) {
            Carrito buscarCarrito = carritoService.findById(id);
            if (buscarCarrito == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(buscarCarrito);
        }

    // Guarda un nuevo carrito
        @GetMapping("/save")
        public ResponseEntity<Carrito> save(Carrito carrito) {
            if (carrito.getIdProducto() == null ) {
                return ResponseEntity.badRequest().build();
            }
            Carrito guardarCarrito = carritoService.save(carrito);
            return ResponseEntity.ok(guardarCarrito);
        }
}
