package com.pizarro777.msvc.comentario.exceptions;



public class ComentarioNotFoundException extends RuntimeException {
    public ComentarioNotFoundException(Long id) {
        super("Comentario no encontrado con id " + id);
    }
}
