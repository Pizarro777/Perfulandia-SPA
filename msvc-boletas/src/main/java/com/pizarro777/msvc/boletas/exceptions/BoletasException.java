package com.pizarro777.msvc.boletas.exceptions;

public class BoletasException extends RuntimeException {
    public BoletasException(String message) {
        super(message);
    }

    public BoletasException(String message, Throwable cause) {
        super(message, cause);
    }
}

