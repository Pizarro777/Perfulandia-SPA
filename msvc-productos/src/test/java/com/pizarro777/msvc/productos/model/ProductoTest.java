package com.pizarro777.msvc.productos.model;

import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import com.pizarro777.msvc.productos.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoTest {

    @Mock
    private ProductoRepository repo;

    @InjectMocks
    private ProductoService service;

    private Producto crearProducto(Long id, String nombre, String marca, String descripcion, Double precio, Integer stock, LocalDate fechaCreacion) {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setFechaCreacion(fechaCreacion);
        return p;
    }


    @Test
    void listarProductos_debeRetornarTodos() {
        Producto p1 = crearProducto(1L, "Perfume Eau de Parfum", "Chanel", "Fragancia femenina floral y elegante", 120.0, 25, LocalDate.now());
        Producto p2 = crearProducto(2L, "Loción Corporal", "Nivea", "Loción hidratante para piel seca", 15.0, 40, LocalDate.now());

        List<Producto> productosMock = List.of(p1, p2);

        when(repo.findAll()).thenReturn(productosMock);

        List<ProductoOutputDTO> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        ProductoOutputDTO esperado1 = ProductoOutputDTO.builder()
                .nombre(p1.getNombre())
                .marca(p1.getMarca())
                .descripcion(p1.getDescripcion())
                .precio(p1.getPrecio())
                .stock(p1.getStock())
                .build();

        assertTrue(resultado.contains(esperado1));


        verify(repo, times(1)).findAll();


    }


}
