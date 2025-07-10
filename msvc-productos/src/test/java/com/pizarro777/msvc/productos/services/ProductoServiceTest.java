package com.pizarro777.msvc.productos.services;

import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
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
        ProductoInputDTO inputDTO = ProductoInputDTO.builder()
                .nombre("Test Producto")
                .marca("MarcaTest")
                .descripcion("Producto de prueba")
                .precio(100.0)
                .stock(5)
                .build();

        Producto productoGuardado = new Producto();
        productoGuardado.setId(10L);
        productoGuardado.setNombre(inputDTO.getNombre());
        productoGuardado.setMarca(inputDTO.getMarca());
        productoGuardado.setDescripcion(inputDTO.getDescripcion());
        productoGuardado.setPrecio(inputDTO.getPrecio());
        productoGuardado.setStock(inputDTO.getStock());
        productoGuardado.setFechaCreacion(LocalDate.now());

        when(repo.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = service.crearProducto(inputDTO);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getId());
        assertEquals("Test Producto", resultado.getNombre());
        assertEquals("Producto de prueba", resultado.getDescripcion());
        assertEquals("MarcaTest", resultado.getMarca());
        assertEquals(100.0, resultado.getPrecio());
        assertEquals(5, resultado.getStock());
    }
}
