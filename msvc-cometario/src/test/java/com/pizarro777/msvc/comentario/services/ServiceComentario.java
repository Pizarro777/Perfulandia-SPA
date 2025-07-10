package com.pizarro777.msvc.comentario.services;


import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.repositories.ComentarioRepository;
import com.pizarro777.msvc.comentario.service.ComentarioService;
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

    @InjectMocks
    private ComentarioService comentarioService;

    private Comentario comentarioPrueba;

    @Test
    void testSaveComentario() {
        Comentario comentario = new Comentario();
        comentario.setComentario("Buen producto");
        comentario.setIdProducto(10L);
        comentario.setFechaCreacion(LocalDate.now());

        when(comentarioRepository.save(any(Comentario.class))).thenReturn(comentario);

        Comentario guardado = comentarioService.save(comentario);

        assertNotNull(guardado);
        assertEquals("Muy buen producto", guardado.getComentario());
        assertEquals(10L, guardado.getIdProducto());
    }
}
