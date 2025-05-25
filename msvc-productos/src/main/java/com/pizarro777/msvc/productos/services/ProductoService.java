package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    /* Crear Producto */
    public Producto crearProducto(Producto producto){
        return repository.save(producto);
    }

    /* Listar Por Id */
    public Producto obtenerPorId(Long id){
        Optional<Producto> producto = repository.findById(id);
        return producto.orElseThrow(()-> new RuntimeException("No se encontro el id en la base de datos: "+ id));
    }

    /* Listar todos */
    public List<Producto> listarTodos(){
        return repository.findAll();
    }

    /* Listar por stock (pendiente)*/

    /* Eliminar Producto */
    public void eliminarProducto(long id){
        repository.deleteById(id);
    }


}
