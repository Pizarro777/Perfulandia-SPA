package com.pizarro777.msvc.inventario.service;

import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.dtos.ProductoDto;
import com.pizarro777.msvc.inventario.dtos.SucursalDto;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import com.pizarro777.msvc.inventario.services.InventarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceImplTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private ProductoClientFeign productoClient;

    @Mock
    private SucursalClientFeign sucursalClient;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    @Test
    void testGuardarInventario() {
        // Arrange
        Inventario inventario = new Inventario();
        inventario.setIdProducto(1L);
        inventario.setIdSucursal(2L);
        inventario.setCantidad(5);

        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre("Producto Test");

        SucursalDto sucursalDto = new SucursalDto();
        sucursalDto.setNombre("Sucursal Test");

        when(inventarioRepository.findByIdProductoAndIdSucursal(1L, 2L))
                .thenReturn(Optional.empty());

        when(productoClient.obtenerProducto(1L)).thenReturn(productoDto);
        when(sucursalClient.obtenerSucursal(2L)).thenReturn(sucursalDto);
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

        // Act
        Inventario resultado = inventarioService.save(inventario);

        // Assert
        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombreProducto());
        assertEquals("Sucursal Test", resultado.getNombreSucursal());
        verify(inventarioRepository).save(inventario);
    }
}
