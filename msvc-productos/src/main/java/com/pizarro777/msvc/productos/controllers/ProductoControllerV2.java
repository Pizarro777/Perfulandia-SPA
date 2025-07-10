package com.pizarro777.msvc.productos.controllers;

import com.pizarro777.msvc.productos.assemblers.ProductoModelAssembler;
import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/api/v2/productos")
@Validated
@Tag(name="Productos V2", description = "Operaciones CRUD sobre productos con HATEOAS")
public class ProductoControllerV2 {

    @Autowired
    private  ProductoService service;

    @Autowired
    private ProductoModelAssembler ProdAssembler;


    /* Crear un nuevo Producto */
    @PostMapping()
    @Operation(
            summary = "Crea un nuevo Producto.",
            description = "Este metodo debe crear un producto nuevo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Producto a crear",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
    )
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
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<EntityModel<Producto>> obtenerProducto(@PathVariable("id") Long id){
        Producto producto = service.obtenerProductoPorId(id);
        EntityModel<Producto> entityModel = ProdAssembler.toModel(producto);
        return ResponseEntity.ok(entityModel);
    }

    /* Obtener todos los productos */
    @GetMapping()
    @Operation(summary = "Lista todos los Productos", description = "Retorna una lista de todos los productos disponibles en el sistema.")
    @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Producto.class))
    )
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> obtenerTodos() {
        List<EntityModel<Producto>> productos = service.obtenerTodos()
                .stream()
                .map(ProdAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Producto>> collection = CollectionModel.of(
                productos,
                linkTo(methodOn(ProductoControllerV2.class).obtenerTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    /* Actualizar Producto */
    @PutMapping("/{id}")
    @Operation(
            summary = "Actualiza un Producto por ID",
            description = "Este metodo debe actualizar un Producto por id, en caso "+
                    "de que no encuentre nada retorna."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se Actualizo el producto ok.",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
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
            @ApiResponse(responseCode = "200", description = "Se elimino el producto ok.",
                    content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Producto no encontrado")
    })
    @Parameters({
            @Parameter(name = "id", description = "ID del producto a eliminar", required = true)
    })
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


}
