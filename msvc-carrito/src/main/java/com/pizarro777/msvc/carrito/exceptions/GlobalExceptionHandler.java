package com.pizarro777.msvc.carrito.exceptions;


import com.pizarro777.msvc.carrito.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Manejo de errores inesperados
    public ResponseEntity<String> handleExceptionGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NoSuchElementException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDate(new Date());

        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        error.setErrors(errors);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
