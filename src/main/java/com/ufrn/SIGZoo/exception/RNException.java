package com.ufrn.SIGZoo.exception;

public class RNException extends RuntimeException {

    public RNException(String message) {
        super("Erro (RN): " + message);
    }

    public RNException(String message, Throwable cause) {
        super("Erro (RN): " + message, cause);
    }
}
