package com.pizarro777.msvc.carrito.Service;

import com.pizarro777.msvc.carrito.model.Carrito;
import com.pizarro777.msvc.carrito.repositories.RepositoryCarrito;
import com.pizarro777.msvc.carrito.service.CarritoService;
import com.pizarro777.msvc.carrito.service.CarritoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceCarrito {

    @Mock
    private RepositoryCarrito repositoryCarrito;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    @Test
    void testGuardarCarrito() {
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);

        when(repositoryCarrito.save(any(Carrito.class))).thenReturn(carrito);

        Carrito resultado = carritoService.save(carrito);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCarrito());
    }
}
