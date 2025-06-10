package com.pizarro777.msvc.carrito.controller;


import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("/api/carritos")
@Validated
@Tag(name = "Carrito", description = "Esra seccion contiene los CRUD de carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

     // Obtienes la lista de todos los carritos
     @GetMapping
     @Operation(
             summary = "Devuelve todos los carritos",
             description = "Este metodo debe retornar un list de Carritos, en caso "+
                     "de que no nada retorna una list vacia"
     )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Se retornaron todos los carritos OK")
     })
     public ResponseEntity<List<Carrito>> findAll() {
         List<Carrito> carritos = carritoService.findAll();
         if (carritos.isEmpty()) {
             return ResponseEntity.noContent().build();
         }
            return ResponseEntity.ok(carritos);
     }

        // Busca un carrito por ID
        @GetMapping("/{id}")
        @Operation(
                summary = "Devuelve todos los carritos",
                description = "Este metodo debe retornar un carrito cuando es consultado"+
                        "mediante su id"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Se retornaron todos los carritos OK"),
                @ApiResponse(responseCode = "400", description = "Error - Carrito con ID no existe ")
        })
        @Parameters(value = {
                @Parameter(name = "id", description = "Este es el id unico de un carrito", required = true)
        })
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
