package com.pizarro777.msvc.productos.model;

import com.pizarro777.msvc.productos.dtos.ProductoInputDTO;
import com.pizarro777.msvc.productos.dtos.ProductoOutputDTO;
import com.pizarro777.msvc.productos.models.Producto;
import com.pizarro777.msvc.productos.repositories.ProductoRepository;
import com.pizarro777.msvc.productos.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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


        verify(repo, times(1)).findAll();
    }

    @Test
    void buscarPorId_existe_debeRetornarProducto() {
        Producto p = crearProducto(5L, "Aromático", "Gucci", "Fragancia fresca para el día", 90.0, 15, LocalDate.now());
        when(repo.findById(5L)).thenReturn(Optional.of(p));

        Producto encontrado = service.obtenerProductoPorId(5L);

        assertNotNull(encontrado);
        assertEquals(5L, encontrado.getId());
        verify(repo).findById(5L);
    }

    @Test
    void buscarPorId_noExiste_lanzaException() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.obtenerPorId(99L)
        );
        assertTrue(ex.getMessage().contains("Producto no encontrado"));
        verify(repo).findById(99L);
    }

    @Test
    void crearProducto_guardaYRetorna() {
        ProductoInputDTO inputDTO = ProductoInputDTO.builder()
                .nombre("Fresca Brisa")
                .marca("Versace")
                .descripcion("Aroma ligero y refrescante")
                .precio(75.0)
                .stock(20)
                .build();

        Producto saved = crearProducto(10L, "Fresca Brisa", "Versace", "Aroma ligero y refrescante", 75.0, 20, LocalDate.now());

        when(repo.save(any(Producto.class))).thenReturn(saved);

        Producto resultado = service.crearProducto(inputDTO);

        assertEquals(10L, resultado.getId());
        assertEquals("Fresca Brisa", resultado.getNombre());
        verify(repo).save(any(Producto.class));
    }


    @Test
    void actualizarProducto_modificaYGuarda() {
        Producto existente = crearProducto(20L, "Clásico", "Armani", "Aroma tradicional", 110.0, 10, LocalDate.now());

        ProductoInputDTO cambiosDTO = ProductoInputDTO.builder()
                .nombre("Moderno")
                .marca("Armani")
                .descripcion("Aroma renovado y fresco")
                .precio(130.0)
                .stock(12)
                .build();

        when(repo.findById(20L)).thenReturn(Optional.of(existente));
        when(repo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ProductoOutputDTO actualizadoDTO = service.actualizarProducto(20L, cambiosDTO);

        assertEquals("Moderno", actualizadoDTO.getNombre());
        assertEquals(130.0, actualizadoDTO.getPrecio());
        assertEquals(12, actualizadoDTO.getStock());
        assertEquals("Armani", actualizadoDTO.getMarca());

        ArgumentCaptor<Producto> captor = ArgumentCaptor.forClass(Producto.class);
        verify(repo).save(captor.capture());
        assertEquals("Moderno", captor.getValue().getNombre());
    }


    @Test
    void eliminarProducto_eliminaCuandoExiste() {
        Producto p = crearProducto(30L, "Exótico", "Calvin Klein", "Fragancia intensa", 140.0, 8, LocalDate.now());
        when(repo.findById(30L)).thenReturn(Optional.of(p));

        assertDoesNotThrow(() -> service.eliminarProducto(30L));

        verify(repo).delete(p);
    }

    @Test
    void eliminarProducto_siNoExiste_lanzaException() {
        when(repo.findById(77L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.eliminarProducto(77L)
        );
        assertTrue(ex.getMessage().contains("Producto no encontrado"));
        verify(repo).findById(77L);
    }



}
