package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    private Producto productoPrueba;

    @Test
    void testSaveProducto() {
        Producto prodTest = new Producto();
        prodTest.setId(10L);
        prodTest.setNombre("Test");
        prodTest.setDescripcion("Test");
        prodTest.setFechaCreacion(LocalDate.now());

        when(repo.save(any(Producto.class))).thenReturn(prodTest);

        Producto guardado = repo.save(prodTest);

        assertNotNull(guardado);
        assertEquals(10L, guardado.getId());
        assertEquals("Test", guardado.getNombre());
        assertEquals("Producto de prueba", guardado.getDescripcion());
        assertEquals("MarcaTest", guardado.getMarca());
        assertEquals(100.0, guardado.getPrecio());
        assertEquals(5, guardado.getStock());
    }
}
