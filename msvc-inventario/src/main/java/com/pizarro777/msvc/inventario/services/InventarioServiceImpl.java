package com.pizarro777.msvc.inventario.services;

import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.dtos.ProductoDto;
import com.pizarro777.msvc.inventario.dtos.SucursalDto;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoClientFeign productoClient;

    @Autowired
    private SucursalClientFeign sucursalClient;

    // Muestra la lista de todos los inventarios
    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    // Busca un inventario por su ID
    @Override
    public Inventario findById(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    // Crear un inventario en la base de datos
    @Override
    public Inventario save(Inventario inventario) {
        Optional<Inventario> existente = inventarioRepository.findByIdProductoAndIdSucursal(
                inventario.getIdProducto(),
                inventario.getIdSucursal()
        );

        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe inventario para este producto y sucursal");
        }

        ProductoDto producto = productoClient.obtenerProducto(inventario.getIdProducto());
        SucursalDto sucursal = sucursalClient.obtenerSucursal(inventario.getIdSucursal());

        inventario.setNombreProducto(producto.getNombre());
        inventario.setNombreSucursal(sucursal.getNombre());

        return inventarioRepository.save(inventario);
    }


    // Actualiza un inventario en la base de datos
    @Override
    public Inventario actualizarInventario(Long id, Inventario inventario) {
        return inventarioRepository.findById(id)
                .map(existingInventario -> {
                    existingInventario.setIdProducto(inventario.getIdProducto());
                    existingInventario.setIdSucursal(inventario.getIdSucursal());
                    existingInventario.setCantidad(inventario.getCantidad());
                    return inventarioRepository.save(existingInventario);
                })
                .orElse(null);
    }

    // Elimina un inventario por su ID
    @Override
    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }
}
