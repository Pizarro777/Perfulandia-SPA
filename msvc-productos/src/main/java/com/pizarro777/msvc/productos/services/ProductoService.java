package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public List<Producto> listarActivos(){
        return productoRepository.findByActivoTrue();
    }

    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarPorId(Long idProducto){
        return productoRepository.findById(idProducto);
    }

    public void eliminar(long idProducto){
        productoRepository.deleteById(idProducto);
    }

}
