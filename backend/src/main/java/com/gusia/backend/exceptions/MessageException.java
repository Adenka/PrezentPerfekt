package com.gusia.backend.exceptions;

public abstract class MessageException extends RuntimeException {
    private final String message;

    public MessageException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
