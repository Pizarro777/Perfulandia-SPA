package com.pizarro777.msvc.comentario.service;

import com.pizarro777.msvc.comentario.clients.ProductoClientRest;
import com.pizarro777.msvc.comentario.model.Comentario;
import com.pizarro777.msvc.comentario.repositories.comentarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ComentarioService {

    @Autowired
    private comentarioRepository comentarioRepository;

    @Autowired
    private ProductoClientRest productoClientRest;

    /* Obtener todos los comentarios de la base de datos */
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    /* Obtener un comentario por su id */
    public Comentario findById(Long idComentario) {
        return comentarioRepository.findById(idComentario).get();
    }

    /* Guardar un comentario en la base de datos */
    public Comentario save(Comentario comentario) {
        //Validar si el producto existe
        productoClientRest.obtenerProductoPorId(comentario.getIdProducto());
        return comentarioRepository.save(comentario);
    }

    /* Elimina un comentario de la base de datos buscado por su ID. */
    public void delete(Long idComentario) {
        comentarioRepository.deleteById(idComentario);
    }
}
