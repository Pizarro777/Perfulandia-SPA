package com.pizarro777.msvc.inventario.services;


import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class InventarioService {

    private final InventarioRepository repository;
    private final ProductoClientFeign productoClientFeign;
    private final SucursalClientFeign sucursalClientFeign;

    @Autowired
    public InventarioService(InventarioRepository repository,
                             ProductoClientFeign productoClientFeign,
                             SucursalClientFeign sucursalClientFeign)
    {
        this.repository = repository;
        this.productoClientFeign = productoClientFeign;
        this.sucursalClientFeign = sucursalClientFeign;
    }



    public Inventario crearInventario(Inventario inventario){
        Long idProducto = inventario.getIdProducto();
        Long idSucursal = inventario.getIdSucursal();

        // Validar que existan producto y sucursal
        boolean productoExiste = existeProducto(idProducto);
        boolean sucursalExiste = existeSucursal(idSucursal);

        if (!productoExiste) {
            throw new RuntimeException("Producto con id " + idProducto + " no existe.");
        }

        if (!sucursalExiste) {
            throw new RuntimeException("Sucursal con id " + idSucursal + " no existe.");
        }

        // Si todo está bien, guardar inventario
        return repository.save(inventario);
    }



}