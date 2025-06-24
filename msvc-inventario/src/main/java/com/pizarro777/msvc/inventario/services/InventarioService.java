package com.pizarro777.msvc.inventario.services;


import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InventarioService {

    private final InventarioRepository repository;
    private final ProductoClientFeign productoClient;
    private final SucursalClientFeign sucursalClient;

    public InventarioService(InventarioRepository repository,
                             ProductoClientFeign productoClient,
                             SucursalClientFeign sucursalClient) {
        this.repository = repository;
        this.productoClient = productoClient;
        this.sucursalClient = sucursalClient;
    }

    /* Crear Inventario con validación */
    public Inventario crearInventario(Inventario inventario) {
        // Validar existencia del producto
        try {
            productoClient.obtenerProducto(inventario.getIdProducto());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe un producto con ID " + inventario.getIdProducto());
        }

        // Validar existencia de la sucursal
        try {
            sucursalClient.obtenerSucursal(inventario.getIdSucursal());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe una sucursal con ID " + inventario.getIdSucursal());
        }

        // Guardar si ambas entidades existen
        return repository.save(inventario);
    }

    /* Obtener por ID */
    public Inventario obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID " + id));
    }

    /* Listar todos */
    public List<Inventario> listarTodos() {
        return repository.findAll();
    }

    /* Actualizar Inventario */
    public Inventario actualizarInventario(Long id, Inventario info) {
        Inventario inv = obtenerPorId(id);

        inv.setIdProducto(info.getIdProducto());
        inv.setIdSucursal(info.getIdSucursal());
        inv.setCantidad(info.getCantidad());
        inv.setDireccion(info.getDireccion());

        return repository.save(inv);
    }

    /* Eliminar */
    public void eliminarInventario(Long id) {
        repository.deleteById(id);
    }

}