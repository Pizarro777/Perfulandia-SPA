package com.pizarro777.msvc.comentario.services;


import com.pizarro777.msvc.comentario.clients.ProductoClientRest;
import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.repositories.ComentarioRepository;
import com.pizarro777.msvc.comentario.service.ComentarioService;
import com.pizarro777.msvc.productos.models.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceComentario {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ProductoClientRest productoClientRest;

    @InjectMocks
    private ComentarioService comentarioService;

    @Test
    void testSaveComentario() {
        Comentario comentario = new Comentario();
        comentario.setComentario("Buen producto");
        comentario.setIdProducto(10L);
        comentario.setFechaCreacion(LocalDate.now());

        // Mock del cliente externo
        Producto productoMock = new Producto();
        productoMock.setId(10L);
        productoMock.setNombre("Mock producto");

        when(productoClientRest.obtenerProducto(10L)).thenReturn(String.valueOf(productoMock));
        when(comentarioRepository.save(any(Comentario.class))).thenReturn(comentario);

        Comentario guardado = comentarioService.save(comentario);

        assertNotNull(guardado);
        assertEquals("Buen producto", guardado.getComentario());
        assertEquals(10L, guardado.getIdProducto());
    }
}

