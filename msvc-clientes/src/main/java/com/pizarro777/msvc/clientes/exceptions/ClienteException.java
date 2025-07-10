package com.pizarro777.msvc.clientes.exceptions;

public class ClienteException extends RuntimeException {

    // Constructor con solo mensaje
    public ClienteException(String message) {
        super(message);
    }

    // Constructor con mensaje y causa (otra excepción)
    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con solo causa
    public ClienteException(Throwable cause) {
        super(cause);
    }
}
