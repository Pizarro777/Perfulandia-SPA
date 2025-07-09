package com.pizarro777.msvc.productos.controllers;

import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import com.pizarro777.msvc.productos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/productos")
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
    @Operation(
            summary = "Crea un nuevo Producto.",
            description = "Este metodo debe crear un producto nuevo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación")
    })
    public ResponseEntity<ProductoOutputDTO> crearProducto(@RequestBody @Validated ProductoInputDTO producto){
        Producto prod = service.crearProducto(producto);
        return ResponseEntity.status(201).body(service.EntityToDTO(prod));
    }

    /* Obtener un producto por ID */
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene un Producto por ID",
            description = "Este metodo debe retornar un Producto, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoOutputDTO> obtenerProducto(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(service.obtenerPorId(id));
    }

    /* Obtener todos los productos */
    @GetMapping()
    @Operation(
            summary = "Lista todos los Productos",
            description = "Retorna una lista de todos los productos disponibles en el sistema."

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "400", description = "Producto no encontrado")
    })
    public ResponseEntity<List<ProductoOutputDTO>> obtenerTodos(){
        return ResponseEntity.status(200).body(service.listarTodos());
    }

    /* Actualizar Producto */
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza un Producto por ID",
            description = "Este metodo debe actualizar un Producto por id, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se Actualizo el producto ok."),
            @ApiResponse(responseCode = "400", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoOutputDTO> actualizarProducto(@PathVariable Long id, @RequestBody @Validated ProductoInputDTO producto){
        return ResponseEntity.ok(service.actualizarProducto(id, producto));
    }


    /* Eliminar un producto por ID */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Elimina un Producto por ID",
            description = "ID del producto a eliminar"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimino el producto ok."),
            @ApiResponse(responseCode = "400", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


}
