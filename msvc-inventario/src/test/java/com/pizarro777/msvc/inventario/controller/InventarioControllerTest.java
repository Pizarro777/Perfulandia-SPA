package com.pizarro777.msvc.inventario.controller;

import com.pizarro777.msvc.inventario.model.Inventario;
import com.pizarro777.msvc.inventario.services.InventarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    private Inventario inventario1;
    private Inventario inventario2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        inventario1 = new Inventario();
        inventario1.setId(1L);
        inventario1.setIdProducto(10L);
        inventario1.setIdSucursal(100L);
        inventario1.setCantidad(5);
        inventario1.setNombreProducto("Producto A");
        inventario1.setNombreSucursal("Sucursal A");

        inventario2 = new Inventario();
        inventario2.setId(2L);
        inventario2.setIdProducto(20L);
        inventario2.setIdSucursal(200L);
        inventario2.setCantidad(10);
        inventario2.setNombreProducto("Producto B");
        inventario2.setNombreSucursal("Sucursal B");
    }

    @Test
    void testFindAll_returnsInventarios() {
        when(inventarioService.findAll()).thenReturn(Arrays.asList(inventario1, inventario2));

        ResponseEntity<List<Inventario>> response = inventarioController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(inventarioService, times(1)).findAll();
    }

    @Test
    void testFindAll_returnsNoContent() {
        when(inventarioService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Inventario>> response = inventarioController.findAll();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(inventarioService, times(1)).findAll();
    }

    @Test
    void testFindById_returnsInventario() {
        when(inventarioService.findById(1L)).thenReturn(inventario1);

        ResponseEntity<Inventario> response = inventarioController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(inventarioService, times(1)).findById(1L);
    }

    @Test
    void testFindById_returnsNotFound() {
        when(inventarioService.findById(99L)).thenReturn(null);

        ResponseEntity<Inventario> response = inventarioController.findById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(inventarioService, times(1)).findById(99L);
    }

    @Test
    void testSave_returnsCreatedInventario() {
        when(inventarioService.save(inventario1)).thenReturn(inventario1);

        ResponseEntity<Inventario> response = inventarioController.save(inventario1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(inventario1.getId(), response.getBody().getId());
        verify(inventarioService, times(1)).save(inventario1);
    }

    @Test
    void testActualizarInventario_returnsUpdatedInventario() {
        when(inventarioService.actualizarInventario(1L, inventario1)).thenReturn(inventario1);

        ResponseEntity<Inventario> response = inventarioController.actualizarInventario(1L, inventario1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(inventario1.getId(), response.getBody().getId());
        verify(inventarioService, times(1)).actualizarInventario(1L, inventario1);
    }

    @Test
    void testActualizarInventario_returnsNotFound() {
        when(inventarioService.actualizarInventario(99L, inventario1)).thenReturn(null);

        ResponseEntity<Inventario> response = inventarioController.actualizarInventario(99L, inventario1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(inventarioService, times(1)).actualizarInventario(99L, inventario1);
    }

    @Test
    void testEliminarInventario_returnsNoContent() {
        doNothing().when(inventarioService).eliminarInventario(1L);

        ResponseEntity<Void> response = inventarioController.eliminarInventario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(inventarioService, times(1)).eliminarInventario(1L);
    }
}
