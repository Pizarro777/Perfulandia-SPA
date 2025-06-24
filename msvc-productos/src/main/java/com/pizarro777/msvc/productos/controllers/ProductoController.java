package com.pizarro777.msvc.productos.controllers;

import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import com.pizarro777.msvc.productos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Validated
@Tag(name="Productos", description = "Esta sección contiene los CRUD de Productos")
public class ProductoController {

    @Autowired
    private final ProductoService service;

    @Autowired
    private ProductoRepository productoRepository;


    public ProductoController(ProductoService service){
        this.service = service;
    }

    /* Crear un nuevo Producto */
    @PostMapping()
    public ResponseEntity<ProductoOutputDTO> crearProducto(@RequestBody @Validated ProductoInputDTO producto){
        Producto prod = service.crearProducto(producto);
        return ResponseEntity.status(201).body(service.EntityToDTO(prod));
    }

    /* Obtener un producto por ID */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoOutputDTO> obtenerProducto(@PathVariable("id") Long id){
        return ResponseEntity.status(201).body(service.obtenerPorId(id));
    }

    /* Obtener todos los productos */
    @GetMapping()
    @Operation(
            summary = "Devuelve todos los productos",
            description = "Este metodo debe retornar un List de Producto, en caso "+
                    "de que no encuentre nada retorna "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los clientes ok"),
            @ApiResponse(responseCode = "400", description = "Error - Producto con ID no existente")
    })
    public ResponseEntity<List<ProductoOutputDTO>> obtenerTodos(){
        return ResponseEntity.status(201).body(service.listarTodos());
    }

    /* Actualizar Producto */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoOutputDTO> actualizarProducto(@PathVariable Long id, @RequestBody @Validated ProductoInputDTO producto){
        return ResponseEntity.ok(service.actualizarProducto(id, producto));
    }

    /* Eliminar un producto por ID */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


}
