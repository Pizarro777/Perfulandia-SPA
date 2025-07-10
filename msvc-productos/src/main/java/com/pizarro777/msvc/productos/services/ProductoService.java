package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    /* Crear Producto */
    public Producto crearProducto(ProductoInputDTO DTO){
        Producto producto = Producto.builder().nombre(DTO.getNombre()).marca(DTO.getMarca())
                .descripcion(DTO.getDescripcion()).precio(DTO.getPrecio()).stock(DTO.getStock()).build();
        return repository.save(producto);
    }

    public Producto obtenerProductoPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    /* Listar Por Id */
    public ProductoOutputDTO obtenerPorId(Long id){
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return EntityToDTO(producto);
    }

    /* Listar todos */
    public List<ProductoOutputDTO> listarTodos(){
        return repository.findAll().stream().map(this::EntityToDTO).collect(Collectors.toList());
    }

    /**
     * Función para poder actualizar un producto por su ID.
     * @param id Identificador del producto.
     * @param datos Ingresa datos nuevos a los valores de un producto.
     * @return retorna un producto los atributos modificados.
     */
    public ProductoOutputDTO actualizarProducto(Long id, ProductoInputDTO datos){
        Producto prod = DTOToEntity(obtenerPorId(id));

        prod.setNombre(datos.getNombre());
        prod.setMarca(datos.getMarca());
        prod.setDescripcion(datos.getDescripcion());
        prod.setPrecio(datos.getPrecio());
        prod.setStock(datos.getStock());

        return EntityToDTO(repository.save(prod));
    }

    /* Eliminar Producto */
    public void eliminarProducto(long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        repository.delete(producto);
    }

    /* Convierte la entidad al DTO */
    public ProductoOutputDTO EntityToDTO(Producto producto){
        ProductoOutputDTO dto = ProductoOutputDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .marca(producto.getMarca())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .fechaCreacion(producto.getFechaCreacion())
                .build();

        return dto;
    }

    /* transfiere datos */
    public Producto DTOToEntity(ProductoOutputDTO dto){
        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .marca(dto.getMarca())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .fechaCreacion(dto.getFechaCreacion())
                .build();

        return producto;
    }

}
