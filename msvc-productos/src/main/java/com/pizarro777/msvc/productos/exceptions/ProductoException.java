package com.pizarro777.msvc.productos.exceptions;

public class ProductoException extends RuntimeException {

    public ProductoException(String message) {
        super(message);
    }

    public ProductoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductoException(Throwable cause) {
        super(cause);
    }
}
