package com.pizarro777.msvc.comentario.service;

import com.pizarro777.msvc.comentario.clients.ProductoClientRest;
import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.repositories.ComentarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ProductoClientRest productoClientRest;

    public ComentarioService(ComentarioRepository comentarioRepository, ProductoClientRest productoClientRest) {
        this.comentarioRepository = comentarioRepository;
        this.productoClientRest = productoClientRest;
    }

    /* Obtener todos los comentarios de la base de datos */
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    /* Obtener un comentario por su id */
    public Comentario findById(Long idComentario) {
        return comentarioRepository.findById(idComentario)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con id " + idComentario));
    }

    /* Guardar un comentario en la base de datos */
    public Comentario save(Comentario comentario) {
        //Validar si el producto existe
        productoClientRest.obtenerProducto(comentario.getIdProducto());
        return comentarioRepository.save(comentario);
    }

    /* Elimina un comentario de la base de datos buscado por su ID. */
    public void delete(Long idComentario) {
        comentarioRepository.deleteById(idComentario);
    }
}
