package com.pizarro777.msvc.inventario.services;

import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository repository;
    private final ProductoClientFeign productoClient;
    private final SucursalClientFeign sucursalClient;

    public InventarioServiceImpl(InventarioRepository repository,
                                 ProductoClientFeign productoClient,
                                 SucursalClientFeign sucursalClient) {
        this.repository = repository;
        this.productoClient = productoClient;
        this.sucursalClient = sucursalClient;
    }

    @Override
    public List<Inventario> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<InventarioDto> listarTodosDto() {
        return repository.findAll()
                .stream()
                .map(inv -> new InventarioDto(
                        inv.getId(),
                        inv.getIdProducto(),
                        inv.getIdSucursal(),
                        inv.getCantidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Inventario obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID " + id));
    }

    @Override
    public Inventario crearInventario(Inventario inventario) {
        try {
            productoClient.obtenerProducto(inventario.getIdProducto());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe un producto con ID " + inventario.getIdProducto());
        }

        try {
            sucursalClient.obtenerSucursal(inventario.getIdSucursal());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe una sucursal con ID " + inventario.getIdSucursal());
        }

        return repository.save(inventario);
    }

    @Override
    public Inventario actualizarInventario(Long id, Inventario datos) {
        Inventario inv = obtenerPorId(id);

        inv.setIdProducto(datos.getIdProducto());
        inv.setIdSucursal(datos.getIdSucursal());
        inv.setCantidad(datos.getCantidad());


        return repository.save(inv);
    }

    @Override
    public void eliminarInventario(Long id) {
        repository.deleteById(id);
    }
    // Devuelve un InventarioDto a partir del ID
    @Override
    public InventarioDto obtenerPorIdDto(Long id) {
        Inventario inv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID " + id));

        return new InventarioDto(
                inv.getId(),
                inv.getIdProducto(),
                inv.getIdSucursal(),
                inv.getCantidad()
        );
    }

    // Crea un inventario a partir de un InventarioDto y devuelve el DTO creado
    @Override
    public InventarioDto crearInventarioDto(InventarioDto dto) {

        try {
            productoClient.obtenerProducto(dto.getIdProducto());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe un producto con ID " + dto.getIdProducto());
        }

        try {
            sucursalClient.obtenerSucursal(dto.getIdSucursal());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("No existe una sucursal con ID " + dto.getIdSucursal());
        }

        // Crear entidad Inventario a partir del DTO
        Inventario inventario = new Inventario();
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setIdSucursal(dto.getIdSucursal());
        inventario.setCantidad(dto.getCantidad());


        Inventario inventarioCreado = repository.save(inventario);

        // Retornar el DTO con el id generado
        return new InventarioDto(
                inventarioCreado.getId(),
                inventarioCreado.getIdProducto(),
                inventarioCreado.getIdSucursal(),
                inventarioCreado.getCantidad()
        );
    }

}