package com.ufrn.SIGZoo.exception;

public class RNException extends RuntimeException {

    public RNException(String message) {
        super(message);
    }

    public RNException(String message, Throwable cause) {
        super(message, cause);
    }
}
