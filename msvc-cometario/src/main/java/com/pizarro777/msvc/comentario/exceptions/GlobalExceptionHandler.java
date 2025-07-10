package com.pizarro777.msvc.comentario.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Manejo de errores inesperados
    public ResponseEntity<String> handleExceptionGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error interno del servidor: " + ex.getMessage());
    }

    @ExceptionHandler(ComentarioNotFoundException.class)
    public ResponseEntity<?> handleComentarioNotFound(ComentarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("status", 404);
        errorAttributes.put("error", "Not Found");
        errorAttributes.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAttributes);
    }

}
