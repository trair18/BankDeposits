package com.gmail.trair8.exception;

public class NoSuchElemInEnumException extends RuntimeException {
    public NoSuchElemInEnumException() {
    }

    public NoSuchElemInEnumException(String message) {
        super(message);
    }

    public NoSuchElemInEnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElemInEnumException(Throwable cause) {
        super(cause);
    }
}