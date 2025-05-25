package com.pizarro777.msvc.inventarios.services;

import com.pizarro777.msvc.inventarios.dtos.InventarioDto;
import com.pizarro777.msvc.inventarios.model.Inventario;
import com.pizarro777.msvc.inventarios.repositories.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoServiceClient productoServiceClient;
    private final SucursalServiceClient sucursalServiceClient;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository,
                             ProductoServiceClient productoServiceClient,
                             SucursalServiceClient sucursalServiceClient) {
        this.inventarioRepository = inventarioRepository;
        this.productoServiceClient = productoServiceClient;
        this.sucursalServiceClient = sucursalServiceClient;
    }

    public Inventario crearOActualizarStock(InventarioDto dto) {
        Long idProducto = dto.getIdProducto();
        Long idSucursal = dto.getIdSucursal();
        int cantidad = dto.getCantidad();
        String ubicacion = dto.getUbicacion();

        // Validar que el producto existe
        if (!productoServiceClient.existeProducto(idProducto)) {
            throw new RuntimeException("Producto no existe con id: " + idProducto);
        }

        // Validar que la sucursal existe
        if (!sucursalServiceClient.existeSucursal(idSucursal)) {
            throw new RuntimeException("Sucursal no existe con id: " + idSucursal);
        }

        // Buscar inventario existente para producto y sucursal
        Optional<Inventario> inventarioOpt = inventarioRepository.findByIdProductoAndIdSucursal(idProducto, idSucursal);

        Inventario inventario;
        if (inventarioOpt.isPresent()) {
            // Actualizar cantidad y ubicación
            inventario = inventarioOpt.get();
            inventario.setCantidad(cantidad);
            inventario.setUbicacion(ubicacion);
        } else {
            // Crear nuevo inventario
            inventario = new Inventario(idProducto, idSucursal, cantidad, ubicacion);
        }

        return inventarioRepository.save(inventario);
    }
    public Inventario obtenerPorProductoYSucursal(Long idProducto, Long idSucursal) {
        return inventarioRepository.findByIdProductoAndIdSucursal(idProducto, idSucursal)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para producto "
                        + idProducto + " y sucursal " + idSucursal));
    }

}