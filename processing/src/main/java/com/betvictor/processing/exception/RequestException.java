package com.betvictor.processing.exception;

public class RequestException extends RuntimeException {

    private static final long serialVersionUID = 277328140424769041L;

    public RequestException(final String errorMessage) {
        super(errorMessage);
    }
}