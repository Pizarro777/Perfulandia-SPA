package com.pizarro777.msvc.inventario.service;

import com.pizarro777.msvc.inventario.clients.ProductoClientFeign;
import com.pizarro777.msvc.inventario.clients.SucursalClientFeign;
import com.pizarro777.msvc.inventario.dtos.InventarioDto;
import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.repositories.InventarioRepository;
import com.pizarro777.msvc.inventario.services.InventarioServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InventarioServiceImplTest {

    private InventarioRepository inventarioRepository;
    private ProductoClientFeign productoClientFeign;
    private SucursalClientFeign sucursalClientFeign;

    private InventarioServiceImpl service;

    @BeforeEach
    void setUp() {
        inventarioRepository = mock(InventarioRepository.class);
        productoClientFeign = mock(ProductoClientFeign.class);
        sucursalClientFeign = mock(SucursalClientFeign.class);
        service = new InventarioServiceImpl(inventarioRepository, productoClientFeign, sucursalClientFeign);
    }

    @Test
    void testCrearInventarioDtoExitosamente() {
        // Given
        InventarioDto dto = new InventarioDto(null, 1L, 2L, 50);

        // Simular que Feign responde bien (no lanza error)
        when(productoClientFeign.obtenerProducto(1L)).thenReturn("OK");
        when(sucursalClientFeign.obtenerSucursal(2L)).thenReturn("OK");

        // Simular guardado exitoso
        Inventario inventarioGuardado = new Inventario(1L, 2L, 50);
        inventarioGuardado.setId(100L);
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioGuardado);

        // When
        InventarioDto resultado = service.crearInventarioDto(dto);

        // Then
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals(1L, resultado.getIdProducto());
        assertEquals(2L, resultado.getIdSucursal());
        assertEquals(50, resultado.getCantidad());
    }

    @Test
    void testCrearInventarioDtoConProductoInvalido() {
        // Given
        InventarioDto dto = new InventarioDto(null, 99L, 2L, 50);

        // Simular que Feign lanza error
        when(productoClientFeign.obtenerProducto(99L))
                .thenThrow(FeignException.NotFound.class);

        // Then
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.crearInventarioDto(dto);
        });

        assertTrue(ex.getMessage().contains("No existe un producto con ID"));
    }

    @Test
    void testCrearInventarioDtoConSucursalInvalida() {
        // Given
        InventarioDto dto = new InventarioDto(null, 1L, 99L, 50);

        // Simular producto OK pero sucursal falla
        when(productoClientFeign.obtenerProducto(1L)).thenReturn("OK");
        when(sucursalClientFeign.obtenerSucursal(99L))
                .thenThrow(FeignException.NotFound.class);

        // Then
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.crearInventarioDto(dto);
        });

        assertTrue(ex.getMessage().contains("No existe una sucursal con ID"));
    }
}