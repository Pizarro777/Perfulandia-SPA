package com.pizarro777.msvc.carrito.Service;

import com.pizarro777.msvc.carrito.clients.ProductoClientRest;
import com.pizarro777.msvc.carrito.dtos.ProductoDTO;
import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.model.ItemCarrito;
import com.pizarro777.msvc.carrito.repositories.RepositoryCarrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
import com.pizarro777.msvc.carrito.service.CarritoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceCarrito {

    @Mock
    private RepositoryCarrito repositoryCarrito;

    @Mock
    private ProductoClientRest productoClientRest;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarCarrito() {
        // Arrange: creamos un carrito con un ítem
        Carrito carrito = new Carrito();
        ItemCarrito item = new ItemCarrito();
        item.setIdProducto(1L);
        item.setCantidad(2);
        item.setCarrito(carrito);
        carrito.getItems().add(item);

        // Mockeamos la respuesta del microservicio de productos
        ProductoDTO productoMock = new ProductoDTO();
        productoMock.setNombre("Producto Mockeado");
        productoMock.setMarca("Marca Falsa");
        productoMock.setPrecio(199.99);

        when(productoClientRest.obtenerProducto(1L)).thenReturn(productoMock);
        when(repositoryCarrito.save(any(Carrito.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Carrito resultado = carritoService.save(carrito);

        // Assert
        assertThat(resultado.getItems()).hasSize(1);
        assertThat(resultado.getItems().get(0).getNombre()).isEqualTo("Producto Mockeado");
        assertThat(resultado.getItems().get(0).getMarca()).isEqualTo("Marca Falsa");
        assertThat(resultado.getItems().get(0).getPrecio()).isEqualTo(199.99);
    }
}
