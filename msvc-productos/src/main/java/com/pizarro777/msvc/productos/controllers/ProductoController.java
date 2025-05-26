package com.pizarro777.msvc.productos.controllers;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import com.pizarro777.msvc.productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {

    @Autowired
    private final ProductoService service;

    private ProductoRepository productoRepository;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    /* Crear un nuevo Producto */
    @PostMapping("/make")
    public ResponseEntity<Producto> crearProducto(@RequestBody @Valid Producto producto){
        Producto prod = service.crearProducto(producto);
        return ResponseEntity.status(201).body(prod);
    }

    /* Obtener un producto por ID */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable("id") Long id){
        Producto prod = service.obtenerPorId(id);
        return ResponseEntity.ok(prod);
    }

    /* Obtener todos los productos */
    @GetMapping("/todos")
    public List<Producto> obtenerTodos(){
        return service.listarTodos();
    }


    /* Eliminar un producto por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


    // ENDPOINT
    @GetMapping("/id/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id){
        return productoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
